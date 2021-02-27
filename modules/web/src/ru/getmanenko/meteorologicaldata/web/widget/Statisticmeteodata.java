package ru.getmanenko.meteorologicaldata.web.widget;

import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.screen.ScreenFragment;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import org.springframework.context.event.EventListener;
import ru.getmanenko.meteorologicaldata.MeteoEvent;
import ru.getmanenko.meteorologicaldata.service.MeteoSettingsService;

import javax.inject.Inject;

@UiController("meteorologicaldata_Statisticmeteodata")
@UiDescriptor("statisticMeteoData.xml")
public class Statisticmeteodata extends ScreenFragment {

    @Inject
    private Label<String> tempOut;
    @Inject
    private Label<String> rain;
    @Inject
    private Label<String> wind;
    @Inject
    private MeteoSettingsService meteoSettingsService;
    @Inject
    private Label<String> press;
    @Inject
    private Label<String> wet;

    @EventListener
    public void onUiNotificationEvent(MeteoEvent event) {
        initFragment();
    }

    @Subscribe
    public void onInit(InitEvent event) {
        initFragment();
    }

    private void initFragment() {
        tempOut.setValue("Температура: " + meteoSettingsService.getMeteoDataOnline().getTa() + " °C");
        rain.setValue("Дождь: " + meteoSettingsService.getMeteoDataOnline().getRi() + " мм/ч");
        wind.setValue("Ветер: " + meteoSettingsService.getMeteoDataOnline().getSm() + " м/с ( " + meteoSettingsService.getMeteoDataOnline().getWindVectorString() + " ) ");
        press.setValue("Давление: " + meteoSettingsService.getMeteoDataOnline().getPa() + " мм рт. ст.");
        wet.setValue("Влажность: " + meteoSettingsService.getMeteoDataOnline().getUa() + " %");
    }

}