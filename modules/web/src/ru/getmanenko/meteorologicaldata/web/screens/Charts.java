package ru.getmanenko.meteorologicaldata.web.screens;

import com.haulmont.addon.helium.web.theme.HeliumThemeVariantsManager;
import com.haulmont.charts.gui.amcharts.model.ChartTheme;
import com.haulmont.charts.gui.components.charts.SerialChart;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.DateField;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import ru.getmanenko.meteorologicaldata.entity.MeteoData;
import ru.getmanenko.meteorologicaldata.service.CreateMapService;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;


@UiController("meteorologicaldata_Charts")
@UiDescriptor("Charts.xml")
@LoadDataBeforeShow
public class Charts extends Screen {

    @Inject
    private CollectionLoader<MeteoData> meteoDatasDl;
    @Inject
    private CreateMapService createMapService;
    @Inject
    private DateField<Date> todo;
    @Inject
    private CollectionContainer<MeteoData> meteoDatasDc;
    @Inject
    private DateField<Date> from;
    @Inject
    private Notifications notifications;

    @Inject
    private SerialChart chartTemp;
    @Inject
    private HeliumThemeVariantsManager heliumThemeVariantsManager;
    @Inject
    private SerialChart chartSpeed;
    @Inject
    private SerialChart chartRain;
    @Inject
    private SerialChart chartPress;
    @Inject
    private SerialChart chartHeat;
    @Inject
    private Label<String> labelTemp;
    @Inject
    private Label<String> labelSpeed;
    @Inject
    private Label<String> labelRain;
    @Inject
    private Label<String> labelPress;
    @Inject
    private Label<String> labelHeat;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    @Subscribe
    protected void onInit(InitEvent event) {
        chartTemp.addZoomListener(zoomEvent ->
                labelTemp.setValue(String.format("Период: <strong>[%s - %s]</strong>",
                        simpleDateFormat.format(zoomEvent.getStartDate()), simpleDateFormat.format(zoomEvent.getEndDate()))));

        chartSpeed.addZoomListener(zoomEvent ->
                labelSpeed.setValue(String.format("Период: <strong>[%s - %s]</strong>",
                        simpleDateFormat.format(zoomEvent.getStartDate()), simpleDateFormat.format(zoomEvent.getEndDate()))));

        chartHeat.addZoomListener(zoomEvent ->
                labelHeat.setValue(String.format("Период: <strong>[%s - %s]</strong>",
                        simpleDateFormat.format(zoomEvent.getStartDate()), simpleDateFormat.format(zoomEvent.getEndDate()))));

        chartPress.addZoomListener(zoomEvent ->
                labelPress.setValue(String.format("Период: <strong>[%s - %s]</strong>",
                        simpleDateFormat.format(zoomEvent.getStartDate()), simpleDateFormat.format(zoomEvent.getEndDate()))));

        chartRain.addZoomListener(zoomEvent ->
                labelRain.setValue(String.format("Период: <strong>[%s - %s]</strong>",
                        simpleDateFormat.format(zoomEvent.getStartDate()), simpleDateFormat.format(zoomEvent.getEndDate()))));
    }


    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        from.setValue(new Date(System.currentTimeMillis()));
        todo.setValue(new Date(System.currentTimeMillis()));
        if (heliumThemeVariantsManager.getUserAppThemeMode() != null) {
            chartTemp.setTheme(ChartTheme.valueOf(heliumThemeVariantsManager.getUserAppThemeMode().toUpperCase()));
            chartSpeed.setTheme(ChartTheme.valueOf(heliumThemeVariantsManager.getUserAppThemeMode().toUpperCase()));
            chartRain.setTheme(ChartTheme.valueOf(heliumThemeVariantsManager.getUserAppThemeMode().toUpperCase()));
            chartPress.setTheme(ChartTheme.valueOf(heliumThemeVariantsManager.getUserAppThemeMode().toUpperCase()));
            chartHeat.setTheme(ChartTheme.valueOf(heliumThemeVariantsManager.getUserAppThemeMode().toUpperCase()));
        }
    }

    public void setDay() {
        getMeteoPeriod(1440, "minute");
    }

    public void setWeek() {
        getMeteoPeriod(10080, "minute");
    }

    public void setMonth() {
        getMeteoPeriod(30240, "minute");
    }

    public void setPeriod() {
        if (from.getValue() == null || todo.getValue() == null) {
            notifications.create().withCaption("Не выбрана дата").show();
        } else {
            String query = String.format("select e from meteorologicaldata_MeteoData e where e.timeResult >= %s and e.timeResult <= %s order by e.timeResult asc", (from.getValue().getTime()), todo.getValue().getTime());
            meteoDatasDl.setQuery(query);
            meteoDatasDl.load();
        }
    }

    private void getMeteoPeriod(int count, String date) {
        String query = String.format("select e from meteorologicaldata_MeteoData e where @between(e.timeResult, now-%d, now, %s) order by e.timeResult asc", count, date);
        meteoDatasDl.setQuery(query);
        meteoDatasDl.load();
    }

    public void resetDateField() {
        from.setValue(null);
        todo.setValue(null);
    }
}