package ru.getmanenko.meteorologicaldata.web.screens.meteosetting;

import com.haulmont.cuba.gui.components.HBoxLayout;
import com.haulmont.cuba.gui.screen.*;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Slider;
import ru.getmanenko.meteorologicaldata.entity.MeteoSetting;
import ru.getmanenko.meteorologicaldata.service.MeteoSettingsService;

import javax.inject.Inject;

@UiController("meteorologicaldata_MeteoSetting.edit")
@UiDescriptor("meteo-setting-edit.xml")
@EditedEntityContainer("meteoSettingDc")
@LoadDataBeforeShow
public class MeteoSettingEdit extends StandardEditor<MeteoSetting> {

    private final Slider slider = new Slider();

    @Inject
    private HBoxLayout spoiler;
    @Inject
    private MeteoSettingsService meteoSettingsService;

    @Subscribe
    public void onInit(InitEvent event) {

        slider.setMin(meteoSettingsService.getScheduledTaskWriteUsb().getPeriod());
        slider.setMax(120);
        slider.setWidth("100%");

        slider.addValueChangeListener(valueChangeEvent -> {
            getEditedEntity().setTimeToUpdate(valueChangeEvent.getValue().intValue());
        });

        spoiler.unwrap(AbstractOrderedLayout.class).addComponent(slider);
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        if (getEditedEntity().getTimeToUpdate() > slider.getMax()) {
            slider.setValue(slider.getMax());
        } else {
            slider.setValue(getEditedEntity().getTimeToUpdate().doubleValue());
        }

    }

}