package ru.getmanenko.meteorologicaldata.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import org.springframework.stereotype.Service;
import ru.getmanenko.meteorologicaldata.entity.MeteoData;

import javax.inject.Inject;
import java.lang.reflect.Field;

@Service(EntityService.NAME)
public class EntityServiceBean implements EntityService {

    @Inject
    private Metadata metadata;
    @Inject
    private DataManager dataManager;
    @Inject
    private MeteoSettingsService meteoSettingsService;

    @Override
    public void createMeteoData() throws IllegalAccessException {

        MeteoData meteoData = metadata.create(MeteoData.class);

        for (Field field : meteoSettingsService.getMeteoDataOnline().getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (!field.getName().equals("serialVersionUID") && !field.getName().equals("windVectorString")) {
                meteoData.setValue(field.getName(), field.get(meteoSettingsService.getMeteoDataOnline()));
            }
        }

        dataManager.commit(meteoData);
    }

}