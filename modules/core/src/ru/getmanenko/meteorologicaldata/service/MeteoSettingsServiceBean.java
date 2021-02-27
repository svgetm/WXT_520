package ru.getmanenko.meteorologicaldata.service;

import com.haulmont.cuba.core.entity.ScheduledTask;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import org.springframework.stereotype.Service;
import ru.getmanenko.meteorologicaldata.entity.MeteoDataOnline;
import ru.getmanenko.meteorologicaldata.entity.MeteoSetting;
import ru.getmanenko.meteorologicaldata.entity.MeteoStationInfo;

import javax.inject.Inject;

@Service(MeteoSettingsService.NAME)
public class MeteoSettingsServiceBean implements MeteoSettingsService {

    @Inject
    private DataManager dataManager;

    // Достаем ентити настроек
    private MeteoSetting getSettings() {
        LoadContext<MeteoSetting> context = LoadContext.create(MeteoSetting.class);
        context.setQuery(LoadContext.createQuery("select e from meteorologicaldata_MeteoSetting e where e.id='b6feac73-a65e-f5bd-3f57-f461106eedcd'"));
        return dataManager.load(context);
    }

    // Достаем назначенную задачу
    @Override
    public ScheduledTask getScheduledTask() {
        LoadContext<ScheduledTask> context = LoadContext.create(ScheduledTask.class);
        context.setQuery(LoadContext.createQuery("select e from sys$ScheduledTask e where e.id='c285e907-7bc2-7a8e-0590-0f6bf812546b'"));
        return dataManager.load(context);
    }

    // Задача по опросу станции
    @Override
    public ScheduledTask getScheduledTaskWriteUsb() {
        LoadContext<ScheduledTask> context = LoadContext.create(ScheduledTask.class);
        context.setQuery(LoadContext.createQuery("select e from sys$ScheduledTask e where e.id='0ab01111-71f2-6dad-405a-3574f1a7cc3c'"));
        return dataManager.load(context);
    }

    // Ентити для виджета
    @Override
    public MeteoDataOnline getMeteoDataOnline() {
        LoadContext<MeteoDataOnline> context = LoadContext.create(MeteoDataOnline.class);
        context.setQuery(LoadContext.createQuery("select e from meteorologicaldata_MeteoDataOnline e where e.id='8c3889bf-fe93-c9a7-ff28-53e80c70ea3a'"));
        return dataManager.load(context);
    }

    @Override
    public MeteoStationInfo getMeteoStationInfo() {
        LoadContext<MeteoStationInfo> context = LoadContext.create(MeteoStationInfo.class);
        context.setQuery(LoadContext.createQuery("select e from meteorologicaldata_MeteoStationInfo e where e.id='7771e3da-1d94-71f8-a01f-f880d6833969'"));
        return dataManager.load(context);
    }

    @Override
    public int getTimeToUpdate(){
        return getSettings().getTimeToUpdate();
    }

    @Override
    public String getComPort(){
        return getSettings().getSelectComPort();
    }

}