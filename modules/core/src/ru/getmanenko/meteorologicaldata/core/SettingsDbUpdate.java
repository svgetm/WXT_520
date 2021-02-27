package ru.getmanenko.meteorologicaldata.core;

import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.entity.ScheduledTask;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Events;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import ru.getmanenko.meteorologicaldata.MeteoSettingsEvent;
import ru.getmanenko.meteorologicaldata.entity.MeteoSetting;
import ru.getmanenko.meteorologicaldata.service.MeteoSettingsService;

import javax.inject.Inject;
import java.util.UUID;

@Component(SettingsDbUpdate.NAME)
public class SettingsDbUpdate {
    public static final String NAME = "meteorologicaldata_SettingsDbUpdate";
    private static int time;
    private static String port;

    @Inject
    private Events events;
    @Inject
    private MeteoSettingsService meteoSettingsService;
    @Inject
    private DataManager dataManager;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onMeteorologicaldata_MeteoSettingBeforCommit(EntityChangedEvent<MeteoSetting, UUID> event) {

        EntityChangedEvent.Type typeEvent = event.getType();

        if (typeEvent == EntityChangedEvent.Type.UPDATED) {
            time = meteoSettingsService.getTimeToUpdate() - 1; // -1 потому что назначенная задача корректно делает это за 59 секунд
            port = meteoSettingsService.getComPort();

        }
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onMeteorologicaldata_MeteoSettingAfterCommit(EntityChangedEvent<MeteoSetting, UUID> event) {

        EntityChangedEvent.Type typeEvent = event.getType();

        if (typeEvent == EntityChangedEvent.Type.UPDATED) {

            if (!meteoSettingsService.getComPort().equals(port)) {
                events.publish(new MeteoSettingsEvent(this));

            } else if (meteoSettingsService.getTimeToUpdate() != time) {
                ScheduledTask scheduledTask = meteoSettingsService.getScheduledTask();

                if (scheduledTask.getActive()){
                    scheduledTask.setActive(false);
                    scheduledTask.setPeriod(meteoSettingsService.getTimeToUpdate() - 1); // отображается у юзера 60 сек, по факту 59 для правильно работы
                    scheduledTask.setActive(true);
                }else {
                    scheduledTask.setPeriod(meteoSettingsService.getTimeToUpdate() - 1);
                }
                dataManager.commit(scheduledTask);
                events.publish(new MeteoSettingsEvent(this));
            }
        }
    }
}