package ru.getmanenko.meteorologicaldata.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "METEOROLOGICALDATA_METEO_DATA_ONLINE")
@Entity(name = "meteorologicaldata_MeteoDataOnline")
public class MeteoDataOnline extends StandardEntity {
    private static final long serialVersionUID = -8206456876348151870L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIME_RESULT")
    protected Date timeResult;

    @Column(name = "WIND_VECTOR_STRING")
    protected String windVectorString;

    @Column(name = "DX")
    protected Double dx;

    @Column(name = "DN")
    protected Double dn;

    @Column(name = "DM")
    protected Double dm;

    @Column(name = "SN")
    protected Double sn;

    @Column(name = "SM")
    protected Double sm;

    @Column(name = "TP")
    protected Double tp;

    @Column(name = "RD")
    protected Double rd;

    @Column(name = "RI")
    protected Double ri;

    @Column(name = "HC")
    protected Double hc;

    @Column(name = "HD")
    protected Double hd;

    @Column(name = "HI")
    protected Double hi;

    @Column(name = "RP")
    protected Double rp;

    @Column(name = "HP")
    protected Double hp;

    @Column(name = "TH")
    protected Double th;

    @Column(name = "VR")
    protected Double vr;

    @Column(name = "SX")
    protected Double sx;

    @Column(name = "TA")
    protected Double ta;

    @Column(name = "UA")
    protected Double ua;

    @Column(name = "PA")
    protected Double pa;

    @Column(name = "RC")
    protected Double rc;

    @Column(name = "VS")
    protected Double vs;

    @Column(name = "VH")
    protected Double vh;

    public Double getVr() {
        return vr;
    }

    public void setVr(Double vr) {
        this.vr = vr;
    }

    public Double getTh() {
        return th;
    }

    public void setTh(Double th) {
        this.th = th;
    }

    public Double getHp() {
        return hp;
    }

    public void setHp(Double hp) {
        this.hp = hp;
    }

    public Double getRp() {
        return rp;
    }

    public void setRp(Double rp) {
        this.rp = rp;
    }

    public Double getHi() {
        return hi;
    }

    public void setHi(Double hi) {
        this.hi = hi;
    }

    public Double getHd() {
        return hd;
    }

    public void setHd(Double hd) {
        this.hd = hd;
    }

    public Double getHc() {
        return hc;
    }

    public void setHc(Double hc) {
        this.hc = hc;
    }

    public Double getRi() {
        return ri;
    }

    public void setRi(Double ri) {
        this.ri = ri;
    }

    public Double getRd() {
        return rd;
    }

    public void setRd(Double rd) {
        this.rd = rd;
    }

    public Double getTp() {
        return tp;
    }

    public void setTp(Double tp) {
        this.tp = tp;
    }

    public Double getSm() {
        return sm;
    }

    public void setSm(Double sm) {
        this.sm = sm;
    }

    public Double getSn() {
        return sn;
    }

    public void setSn(Double sn) {
        this.sn = sn;
    }

    public Double getDm() {
        return dm;
    }

    public void setDm(Double dm) {
        this.dm = dm;
    }

    public Double getDn() {
        return dn;
    }

    public void setDn(Double dn) {
        this.dn = dn;
    }

    public Double getVh() {
        return vh;
    }

    public void setVh(Double vh) {
        this.vh = vh;
    }

    public Double getVs() {
        return vs;
    }

    public void setVs(Double vs) {
        this.vs = vs;
    }

    public Double getRc() {
        return rc;
    }

    public void setRc(Double rc) {
        this.rc = rc;
    }

    public Double getPa() {
        return pa;
    }

    public void setPa(Double pa) {
        this.pa = pa;
    }

    public Double getUa() {
        return ua;
    }

    public void setUa(Double ua) {
        this.ua = ua;
    }

    public Double getTa() {
        return ta;
    }

    public void setTa(Double ta) {
        this.ta = ta;
    }

    public Double getSx() {
        return sx;
    }

    public void setSx(Double sx) {
        this.sx = sx;
    }

    public Double getDx() {
        return dx;
    }

    public void setDx(Double dx) {
        this.dx = dx;
    }


    public String getWindVectorString() {
        return windVectorString;
    }

    public void setWindVectorString(String windVectorString) {
        this.windVectorString = windVectorString;
    }

    public Date getTimeResult() {
        return timeResult;
    }

    public void setTimeResult(Date timeResult) {
        this.timeResult = timeResult;
    }
}