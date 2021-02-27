package ru.getmanenko.meteorologicaldata.web.screens.meteosetting;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.entity.ScheduledTask;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.gui.screen.LookupComponent;
import org.springframework.context.event.EventListener;
import ru.getmanenko.meteorologicaldata.MeteoSettingsEvent;
import ru.getmanenko.meteorologicaldata.entity.MeteoSetting;
import ru.getmanenko.meteorologicaldata.service.ConnectService;
import ru.getmanenko.meteorologicaldata.service.CreateMapService;
import ru.getmanenko.meteorologicaldata.service.MeteoSettingsService;

import javax.inject.Inject;
import java.util.Objects;

@UiController("meteorologicaldata_MeteoSetting.browse")
@UiDescriptor("meteo-setting-browse.xml")
@LookupComponent("meteoSettingsTable")
@LoadDataBeforeShow
public class MeteoSettingBrowse extends StandardLookup<MeteoSetting> {

    private final String WIND_COMMAND = "WU";
    private final String TEMP_COMMAND = "TU";
    private final String WET_COMMAND = "RU";
    private final String VIZOR_COMMAND = "SU";
    private static final String REQUEST_HARD_RESET = "0XZ!";

    @Inject
    private CollectionLoader<MeteoSetting> meteoSettingsDl;
    @Inject
    private Notifications notifications;
    @Inject
    private Messages messages;
    @Inject
    private LookupField<Integer> modeWind;
    @Inject
    private LookupField<String> unitSpeed;
    @Inject
    private LookupField<Integer> samplingFrequency;
    @Inject
    private LookupField<String> formatWind;
    @Inject
    private LookupField<String> Pa;
    @Inject
    private LookupField<String> unitT;
    @Inject
    private CreateMapService createMapService;
    @Inject
    private LookupField<String> unitWet;
    @Inject
    private LookupField<String> unitKick;
    @Inject
    private LookupField<String> modeSend;
    @Inject
    private LookupField<String> resetCount;
    @Inject
    private LookupField<String> controlHit;
    @Inject
    private TextField<Integer> settingsWindCorrect;
    @Inject
    private TextField<Integer> settingsWind;
    @Inject
    private LookupField<String> messError;
    @Inject
    private TextField<Integer> settingsWindPerMidl;
    @Inject
    private TextField<Integer> settingsTempPerRef;
    @Inject
    private TextField<Integer> settingsWetPredelWet;
    @Inject
    private TextField<Integer> settingsWetPredelRain;
    @Inject
    private TextField<Integer> settingsWetPerRef;
    @Inject
    private Label<String> infoModeOne;
    @Inject
    private TextField<Integer> settingsVizorPerRef;
    @Inject
    private Label<String> infoModeTwo;
    @Inject
    private ConnectService connectService;
    @Inject
    private TextArea<String> infoStation;
    @Inject
    private MeteoSettingsService meteoSettingsService;
    @Inject
    private CollectionLoader<ScheduledTask> scheduledTasksDl;
    @Inject
    private Table<ScheduledTask> scheduledTasksTable;
    @Inject
    private DataManager dataManager;
    @Inject
    private UiComponents uiComponents;

    @Subscribe
    public void onInit(InitEvent event) {

        // Инфо для лейблов
        infoModeOne.setValue(messages.getMessage("ru.getmanenko.meteorologicaldata.web", "infoLabelOne"));
        infoModeTwo.setValue(messages.getMessage("ru.getmanenko.meteorologicaldata.web", "infoLabelTwo"));

        // Датчик ветра
        modeWind.setOptionsMap(createMapService.mapForWind());
        modeWind.setPopupWidth("200%");
        unitSpeed.setOptionsMap(createMapService.mapForUtit());
        formatWind.setOptionsMap(createMapService.mapForFormatWind());
        samplingFrequency.setOptionsMap(createMapService.sampling());
        // Датчик температуры и давления
        Pa.setOptionsMap(createMapService.mapForPa());
        unitT.setOptionsMap(createMapService.mapForTemp());
        // Датчик осадков
        unitKick.setOptionsMap(createMapService.mapForUnitKick());
        unitWet.setOptionsMap(createMapService.mapForUnitWet());
        modeSend.setOptionsMap(createMapService.mapForModeSend());
        resetCount.setOptionsMap(createMapService.mapForResetCount());
        //Датчик супервизор
        messError.setOptionsMap(createMapService.mapForMessError());
        controlHit.setOptionsMap(createMapService.mapForControlHit());

    }

    @Subscribe("Reset")
    public void onResetClick(Button.ClickEvent event) {
        if (meteoSettingsService.getScheduledTaskWriteUsb().getActive()){
            notifications.create().withCaption("Необходимо выключить функцию опроса станции").show();
        }else {
            hardReset();
        }
    }

    @EventListener
    public void onUiNotificationEvent(MeteoSettingsEvent event) {
        meteoSettingsDl.load();
        scheduledTasksDl.load();
        notifications.create().withCaption(messages.getMessage("ru.getmanenko.meteorologicaldata.web", "updateSetting"))
                .show();
    }

    // Методы для кнопок датчика ветра

    public void periodRefreshWind() {
        checkOnNull(String.valueOf(settingsWind.getValue()), WIND_COMMAND, "I", String.valueOf(settingsWind.getValue()));
    }

    public void periodMidlWind() {
        checkOnNull(String.valueOf(settingsWindPerMidl.getValue()), WIND_COMMAND, "A", String.valueOf(settingsWindPerMidl.getValue()));
    }

    public void selectModeWind() {
        checkOnNull(String.valueOf(modeWind.getValue()), WIND_COMMAND, "G", String.valueOf(modeWind.getValue()));
    }

    public void selectUnitWind() {
        checkOnNull(unitSpeed.getValue(), WIND_COMMAND, "U", unitSpeed.getValue());
    }

    public void correctionDirection() {
        checkOnNull(String.valueOf(settingsWindCorrect.getValue()), WIND_COMMAND, "D", String.valueOf(settingsWindCorrect.getValue()));
    }

    public void formatParamsWind() {
        checkOnNull(formatWind.getValue(), WIND_COMMAND, "N", formatWind.getValue());
    }

    public void samplingFrequency() {
        checkOnNull(String.valueOf(samplingFrequency.getValue()), WIND_COMMAND, "F", String.valueOf(samplingFrequency.getValue()));
    }

    // Методы для кнопок датчика температуры
    public void periodRefreshTemp() {
        checkOnNull(String.valueOf(settingsTempPerRef.getValue()), TEMP_COMMAND, "I", String.valueOf(settingsTempPerRef.getValue()));
    }

    public void selectUtitPa() {
        checkOnNull(Pa.getValue(), TEMP_COMMAND, "P", Pa.getValue());
    }

    public void selectUtitTemp() {
        checkOnNull(unitT.getValue(), TEMP_COMMAND, "T", unitT.getValue());
    }

    // Методы для кнопок датчика осадков
    public void periodRefreshWet() {
        checkOnNull(String.valueOf(settingsWetPerRef.getValue()), WET_COMMAND, "I", String.valueOf(settingsWetPerRef.getValue()));
    }

    public void selectUnitWet() {
        checkOnNull(unitWet.getValue(), WET_COMMAND, "U", unitWet.getValue());
    }

    public void selectUnitKick() {
        checkOnNull(unitKick.getValue(), WET_COMMAND, "S", unitKick.getValue());
    }

    public void selectModeSend() {
        checkOnNull(modeSend.getValue(), WET_COMMAND, "M", modeSend.getValue());
    }

    public void resetCount() {
        checkOnNull(resetCount.getValue(), WET_COMMAND, "Z", resetCount.getValue());
    }

    public void predelRain() {
        checkOnNull(String.valueOf(settingsWetPredelRain.getValue()), WET_COMMAND, "X", String.valueOf(settingsWetPredelRain.getValue()));
    }

    public void predelWet() {
        checkOnNull(String.valueOf(settingsWetPredelWet.getValue()), WET_COMMAND, "Y", String.valueOf(settingsWetPredelWet.getValue()));
    }


    // Методы для кнопок датчика супревизора
    public void periodRefreshVizor() {
        checkOnNull(String.valueOf(settingsVizorPerRef.getValue()), VIZOR_COMMAND, "I", String.valueOf(settingsVizorPerRef.getValue()));
    }

    public void enableMessageError() {
        checkOnNull(messError.getValue(), VIZOR_COMMAND, "S", messError.getValue());
    }

    public void hitControl() {
        checkOnNull(controlHit.getValue(), VIZOR_COMMAND, "H", controlHit.getValue());
    }


    private void checkOnNull(String obj, String command, String param, String data) {
        if (meteoSettingsService.getScheduledTaskWriteUsb().getActive()){
            notifications.create().withCaption("Необходимо выключить функцию опроса станции").show();
        }
        else {
            if (obj == null || obj.equals("null")) {
                notifications.create().withCaption(messages.getMessage("ru.getmanenko.meteorologicaldata.web", "dontSelect"))
                        .show();
            } else {
                connectService.writeCommandToUsb("0", 0, command, param, data);
                notifications.create().withCaption(messages.getMessage("ru.getmanenko.meteorologicaldata.web", "updateSettingsSensor"))
                        .show();
            }
        }
    }

    public void hardReset() {

        connectService.writeCommandToUsb(REQUEST_HARD_RESET, 0, "0", "0", "0");
    }

    public void getInfoStation() {
        if (meteoSettingsService.getScheduledTaskWriteUsb().getActive()){
            notifications.create().withCaption("Необходимо выключить функцию опроса станции").show();
        }
        else {
            connectService.getInfoFromUsb("0XF!");
            infoStation.setValue(
                    "Серийный номер: " + meteoSettingsService.getMeteoStationInfo().getSerialNumber() + "\n" +
                            "Дата калибровки: " + meteoSettingsService.getMeteoStationInfo().getCalibrity() + "\n" +
                            "Заводские опции: " + meteoSettingsService.getMeteoStationInfo().getOptions() + "\n" +
                            "Код заказа: " + meteoSettingsService.getMeteoStationInfo().getCode() + "\n" +
                            "Информация: " + meteoSettingsService.getMeteoStationInfo().getInfo());
        }
    }

    public void activeTask() {
        if (scheduledTasksTable.getSingleSelected() != null){
            if (!scheduledTasksTable.getSingleSelected().getActive()){
                scheduledTasksTable.getSingleSelected().setActive(true);
                ScheduledTask task = meteoSettingsService.getScheduledTaskWriteUsb();
                task.setActive(true);
                dataManager.commit(task);
            }
            else{
                scheduledTasksTable.getSingleSelected().setActive(false);
                ScheduledTask task = meteoSettingsService.getScheduledTask();
                task.setActive(false);
                dataManager.commit(task);
            }
            dataManager.commit(scheduledTasksTable.getSingleSelected());
            scheduledTasksDl.load();
        }else notifications.create().withCaption("Не выбрана задача").show();
    }

    public Component setValueTask(Entity entity) {
        Label label = uiComponents.create(Label.NAME);
        if (((ScheduledTask) entity).getMethodName().equals("writeUsb")){
            label.setValue((entity.getValue("period")));
        }else {
            label.setValue(((int) entity.getValue("period")) + 1);
        }

        return label;
    }
}