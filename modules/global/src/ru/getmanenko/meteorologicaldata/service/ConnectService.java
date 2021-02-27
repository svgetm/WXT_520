package ru.getmanenko.meteorologicaldata.service;

import jssc.SerialPortException;

public interface ConnectService {
    String NAME = "meteorologicaldata_ConnectService";

    void writeUsb(String req);
    void getInfoFromUsb(String req);
    void writeCommandToUsb(String request, int address, String command, String param, String data);

}