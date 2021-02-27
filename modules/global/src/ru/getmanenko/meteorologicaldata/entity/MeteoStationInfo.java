package ru.getmanenko.meteorologicaldata.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "METEOROLOGICALDATA_METEO_STATION_INFO")
@Entity(name = "meteorologicaldata_MeteoStationInfo")
public class MeteoStationInfo extends StandardEntity {
    private static final long serialVersionUID = 8576972617743914260L;

    @Column(name = "OPTIONS")
    private String options;

    @Column(name = "CODE")
    private String code;

    @Column(name = "CALIBRITY")
    private String calibrity;

    @Column(name = "INFO")
    private String info;

    @Column(name = "SERIAL_NUMBER")
    private String serialNumber;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCalibrity() {
        return calibrity;
    }

    public void setCalibrity(String calibrity) {
        this.calibrity = calibrity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }
}