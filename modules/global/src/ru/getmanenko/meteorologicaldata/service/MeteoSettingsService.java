package ru.getmanenko.meteorologicaldata.service;

import com.haulmont.cuba.core.entity.ScheduledTask;
import ru.getmanenko.meteorologicaldata.entity.MeteoDataOnline;
import ru.getmanenko.meteorologicaldata.entity.MeteoStationInfo;

public interface MeteoSettingsService {
    String NAME = "meteorologicaldata_MeteoSettingsService";
    int getTimeToUpdate();
    String getComPort();
    MeteoDataOnline getMeteoDataOnline();
    ScheduledTask getScheduledTask();
    MeteoStationInfo getMeteoStationInfo();
    ScheduledTask getScheduledTaskWriteUsb();
}