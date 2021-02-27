package ru.getmanenko.meteorologicaldata.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "METEOROLOGICALDATA_METEO_SETTING")
@Entity(name = "meteorologicaldata_MeteoSetting")
@PublishEntityChangedEvents

public class MeteoSetting extends StandardEntity {
    private static final long serialVersionUID = -4866775677305044376L;

    @Column(name = "TIME_TO_UPDATE")
    protected Integer timeToUpdate;

    @Column(name = "SELECT_COM_PORT")
    protected String selectComPort;

    public String getSelectComPort() {
        return selectComPort;
    }

    public void setSelectComPort(String selectComPort) {
        this.selectComPort = selectComPort;
    }

    public Integer getTimeToUpdate() {
        return timeToUpdate;
    }

    public void setTimeToUpdate(Integer timeToUpdate) {
        this.timeToUpdate = timeToUpdate;
    }
}