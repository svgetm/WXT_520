package ru.getmanenko.meteorologicaldata.web.screens.meteodata;

import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.DateField;
import com.haulmont.cuba.gui.components.Filter;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import org.springframework.context.event.EventListener;
import ru.getmanenko.meteorologicaldata.MeteoEvent;
import ru.getmanenko.meteorologicaldata.entity.MeteoData;
import ru.getmanenko.meteorologicaldata.service.ConnectService;

import javax.inject.Inject;
import javax.persistence.Query;
import java.util.Date;

@UiController("meteorologicaldata_MeteoData.browse")
@UiDescriptor("meteo-data-browse.xml")
@LookupComponent("meteoDatasTable")
@LoadDataBeforeShow
public class MeteoDataBrowse extends StandardLookup<MeteoData> {
    private static final String REQUEST_INFO = "0R0!";


    @Inject
    private DateField<Date> from;
    @Inject
    private ConnectService connectService;
    @Inject
    private CollectionLoader<MeteoData> meteoDatasDl;
    @Inject
    private DateField<Date> todo;
    @Inject
    private Notifications notifications;
    @Inject
    private Filter filterMeteo;

    @Subscribe
    private void onInit(InitEvent event) {
        filterMeteo.setMaxResults(9999);
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        from.setValue(new Date(System.currentTimeMillis()));
        todo.setValue(new Date(System.currentTimeMillis()));
    }

    @EventListener
    public void onUiNotificationEvent(MeteoEvent event) {
        meteoDatasDl.load();
    }


    public void start() {

        connectService.writeUsb(REQUEST_INFO);
    }


    public void setDay() {
        getMeteoPeriod(1440, "minute");
    }

    public void setWeek() {
        getMeteoPeriod(10080,"minute");
    }


    public void setMonth() {
        getMeteoPeriod(43800,"minute");
    }

    public void setYear() {
        getMeteoPeriod(525600,"minute");
    }

    public void setAllTime() {
        meteoDatasDl.setQuery("select e from meteorologicaldata_MeteoData e order by e.timeResult desc");
        meteoDatasDl.setMaxResults(0);
        meteoDatasDl.load();
    }

    public void setPeriod() {
        if (from.getValue() == null || todo.getValue() == null) {
            notifications.create().withCaption("Не выбрана дата").show();
        } else {
            String query = String.format("select e from meteorologicaldata_MeteoData e where e.timeResult >= %s and e.timeResult <= %s order by e.timeResult desc", (from.getValue().getTime()), todo.getValue().getTime());
            meteoDatasDl.setQuery(query);
            meteoDatasDl.load();
        }
    }

    private void getMeteoPeriod(int count, String date) {
        String query = String.format("select e from meteorologicaldata_MeteoData e where @between(e.timeResult, now-%d, now, %s) order by e.timeResult desc", count, date);
        meteoDatasDl.setQuery(query);
        meteoDatasDl.load();
    }

    public void resetDateField() {
        from.setValue(null);
        todo.setValue(null);
    }
}