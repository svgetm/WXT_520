package ru.getmanenko.meteorologicaldata.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.security.app.Authentication;
import jssc.*;
import org.springframework.stereotype.Service;
import ru.getmanenko.meteorologicaldata.entity.MeteoDataOnline;
import ru.getmanenko.meteorologicaldata.entity.MeteoStationInfo;

import javax.inject.Inject;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service(ConnectService.NAME)
public class ConnectServiceBean implements ConnectService {

    private static SerialPort serialPort;


    @Inject
    private Metadata metadata;
    @Inject
    private DataManager dataManager;
    @Inject
    private MeteoSettingsService meteoSettingsService;
    @Inject
    private Authentication authentication;
    @Inject
    private Messages messages;

    @Override
    public void getInfoFromUsb(String req) {

        serialPort = new SerialPort(meteoSettingsService.getComPort());
        try {
            //Открываем порт
            serialPort.openPort();
            //Выставляем параметры
            serialPort.setParams(SerialPort.BAUDRATE_19200,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            //Устанавливаем ивент лисенер и маску
            serialPort.addEventListener(new PortReaderForInfo(dataManager, metadata, meteoSettingsService,
                    authentication, messages), SerialPort.MASK_RXCHAR);
            //Отправляем запрос устройству
            serialPort.writeString(req);

        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void writeUsb(String req) {

        serialPort = new SerialPort(meteoSettingsService.getComPort());
        try {
            //Открываем порт
            serialPort.openPort();
            //Выставляем параметры
            serialPort.setParams(SerialPort.BAUDRATE_19200,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            //Устанавливаем ивент лисенер и маску
            serialPort.addEventListener(new PortReader(dataManager, meteoSettingsService,
                    authentication, messages), SerialPort.MASK_RXCHAR);
            //Отправляем запрос устройству
            serialPort.writeString(req);

        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeCommandToUsb(String request, int address, String command, String param, String data) {
        serialPort = new SerialPort(meteoSettingsService.getComPort());
        try {
            //Открываем порт
            serialPort.openPort();
            //Выставляем параметры
            serialPort.setParams(SerialPort.BAUDRATE_19200,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            //Отправляем запрос устройству
            if (!request.equals("0")) {
                serialPort.writeString(request);
            } else {
                serialPort.writeString(String.format("%d%s,%s=%s!", address, command, param, data));
            }

            serialPort.closePort();

        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    private static class PortReader implements SerialPortEventListener {

        @Inject
        private DataManager dataManager;
        @Inject
        private MeteoSettingsService meteoSettingsService;
        @Inject
        private Authentication authentication;
        @Inject
        private Messages messages;


        public PortReader(DataManager dataManager, MeteoSettingsService meteoSettingsService, Authentication authentication, Messages message) {
            this.dataManager = dataManager;
            this.meteoSettingsService = meteoSettingsService;
            this.authentication = authentication;
            this.messages = message;
        }

        public void serialEvent(SerialPortEvent event) {
            if (event.isRXCHAR() && event.getEventValue() > 0) {
                try {

                    // Необходимо что бы буфер был полностью заполнен ответом от станции
                    Thread.sleep(250);
                    //Получаем ответ от устройства, обрабатываем данные и т.д.
                    String request = serialPort.readString();

                    String[] data = request.substring(4).split(",");

                    //Массив с локализацией для направления ветра
                    String[] vector = {messages.getMessage("ru.getmanenko.meteorologicaldata", "N"),
                            messages.getMessage("ru.getmanenko.meteorologicaldata", "NNE"),
                            messages.getMessage("ru.getmanenko.meteorologicaldata", "NE"),
                            messages.getMessage("ru.getmanenko.meteorologicaldata", "ENE"),
                            messages.getMessage("ru.getmanenko.meteorologicaldata", "E"),
                            messages.getMessage("ru.getmanenko.meteorologicaldata", "ESE"),
                            messages.getMessage("ru.getmanenko.meteorologicaldata", "SE"),
                            messages.getMessage("ru.getmanenko.meteorologicaldata", "SSE"),
                            messages.getMessage("ru.getmanenko.meteorologicaldata", "S"),
                            messages.getMessage("ru.getmanenko.meteorologicaldata", "SSW"),
                            messages.getMessage("ru.getmanenko.meteorologicaldata", "SW"),
                            messages.getMessage("ru.getmanenko.meteorologicaldata", "WSW"),
                            messages.getMessage("ru.getmanenko.meteorologicaldata", "W"),
                            messages.getMessage("ru.getmanenko.meteorologicaldata", "WNW"),
                            messages.getMessage("ru.getmanenko.meteorologicaldata", "NW"),
                            messages.getMessage("ru.getmanenko.meteorologicaldata", "NNW")};

                    HashMap<String, Double> meteoDataMap = new HashMap<>();

                    for (int i = 0; i < data.length - 1; i++) {
                        String[] dataNew = data[i].split("=");
                        meteoDataMap.put(dataNew[0], Double.parseDouble(dataNew[1].substring(0, dataNew[1].length() - 1)));
                    }

                    // Инт нужен для взятия из массива нужное напрвления ветра, где из мапы берется угол
                    // полученный от метеостанции
                    int result = (int) ((((meteoDataMap.get("Dm") + 11.25 / 2) / 22.5) % 16));

                    // Здесь сеттаем ентити для виджета
                    authentication.begin("admin");
                    try {
                        MeteoDataOnline meteoDataOnline = meteoSettingsService.getMeteoDataOnline();

                        meteoDataOnline.setTimeResult(new Date(System.currentTimeMillis()));
                        for (Map.Entry<String, Double> map : meteoDataMap.entrySet()) {
                            meteoDataOnline.setValue(map.getKey().toLowerCase(), map.getValue());
                        }
                        //сеттаем направление ветра для виджета
                        meteoDataOnline.setWindVectorString(vector[result]);

                        dataManager.commit(meteoDataOnline);
                    } catch (Exception ignore) {

                    } finally {
                        authentication.end();
                    }

                    // Если интенсивности дождя нет, то сбрасываем параметры у станции
                    if (meteoDataMap.get("Ri") == 0) {
                        // Сброс количества осадков
                        serialPort.writeString("0XZRU!");
                        // Сброс интенсивности осадков
                        serialPort.writeString("0XZRI!");
                    }

                    serialPort.closePort();
                } catch (SerialPortException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class PortReaderForInfo implements SerialPortEventListener {

        @Inject
        private Metadata metadata;
        @Inject
        private DataManager dataManager;
        @Inject
        private MeteoSettingsService meteoSettingsService;
        @Inject
        private Authentication authentication;
        @Inject
        private Messages messages;


        public PortReaderForInfo(DataManager dataManager, Metadata metadata, MeteoSettingsService meteoSettingsService, Authentication authentication, Messages message) {
            this.dataManager = dataManager;
            this.metadata = metadata;
            this.meteoSettingsService = meteoSettingsService;
            this.authentication = authentication;
            this.messages = message;
        }

        public void serialEvent(SerialPortEvent event) {
            if (event.isRXCHAR() && event.getEventValue() > 0) {
                try {

                    // Необходимо что бы буфер был полностью заполнен ответом от станции
                    Thread.sleep(250);
                    //Получаем ответ от устройства, обрабатываем данные и т.д.
                    String request = serialPort.readString();

                    String [] data = request.substring(4).split(",");

                    HashMap<String, String> info = new HashMap<>();

                    for (int i = 0; i < data.length - 1; i++) {
                        String[] dataNew = data[i].split("=");
                        info.put(dataNew[0], (dataNew[1]));
                    }


                    // Здесь сеттаем ентити для инфы
                    authentication.begin("admin");
                    try {
                        MeteoStationInfo meteoStationInfo = meteoSettingsService.getMeteoStationInfo();

                        meteoStationInfo.setSerialNumber(info.get("n"));
                        meteoStationInfo.setCalibrity(info.get("c"));
                        meteoStationInfo.setCode(info.get("o"));
                        meteoStationInfo.setInfo(info.get("i"));
                        meteoStationInfo.setOptions(info.get("f"));

                        dataManager.commit(meteoStationInfo);

                    } catch (Exception ignore) {

                    } finally {
                        authentication.end();
                    }

                    serialPort.closePort();
                } catch (SerialPortException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}