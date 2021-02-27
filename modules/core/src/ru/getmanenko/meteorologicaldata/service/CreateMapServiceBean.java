package ru.getmanenko.meteorologicaldata.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service(CreateMapService.NAME)
public class CreateMapServiceBean implements CreateMapService {

    @Override
    public List<String> listVector() {
        LinkedList<String> vector = new LinkedList<>();
        vector.add("N");
        vector.add("NNE");
        vector.add("NE");
        vector.add("ENE");
        vector.add("E");
        vector.add("ESE");
        vector.add("SE");
        vector.add("SSE");
        vector.add("S");
        vector.add("SSW");
        vector.add("SW");
        vector.add("WSW");
        vector.add("W");
        vector.add("WNW");
        vector.add("NW");
        vector.add("NNW");

        return vector;
    }


    @Override
    public Map<String, Integer> mapForWind(){

        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("Традиционный режим выбора максимального/минимального значения как для скорости, так и для направления.", 1);
        map.put("Выбор скорости ветра по принципу порыв и затишье (Gust & lull), при этом направление выбирается как при традиционном режиме. " +
                "В сообщении происходит замена макимальной/минимальной скорости ветра в (Sx, Sn), соответственно.", 3);

        return map;
    }

    @Override
    public Map<String, String> mapForUtit(){
        Map<String, String> map = new LinkedHashMap<>();
        map.put("м/с","M");
        map.put("км/ч","K");
        map.put("мили","S");
        map.put("узлы","N");

        return map;
    }

    @Override
    public Map<String, String> mapForFormatWind(){
        Map<String, String> map = new LinkedHashMap<>();
        map.put("XDR (синтаксис преобразователя)","T");
        map.put("MWV (скорость и угол ветра)","W");

        return map;
    }

    @Override
    public Map<String, Integer> sampling(){

        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("1 Гц", 1);
        map.put("2 Гц", 2);
        map.put("4 Гц", 4);

        return map;
    }

    @Override
    public Map<String, String> mapForPa (){
        Map<String, String> map = new LinkedHashMap<>();
        map.put("гПа","H");
        map.put("Паскаль","P");
        map.put("бар","B");
        map.put("мм рт.ст.","M");
        map.put("дюймы рт.ст.","I");

        return map;
    }

    @Override
    public Map<String, String> mapForTemp(){
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Цельсий","C");
        map.put("Фаренгейт","F");

        return map;
    }

    @Override
    public Map<String, String> mapForUnitWet(){
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Метрические","M");
        map.put("Английские","I");

        return map;
    }

    @Override
    public Map<String, String> mapForUnitKick(){
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Метрические","M");
        map.put("Английские","I");

        return map;
    }

    @Override
    public Map<String, String> mapForModeSend(){
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Осадки on/off","R");
        map.put("Опрокидывающийся сосуд","C");
        map.put("Временная база","T");

        return map;
    }

    @Override
    public Map<String, String> mapForResetCount(){
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Ручной","M");
        map.put("Предел","L");
        map.put("Автоматический","A");
        map.put("Мгновенный","Y");

        return map;
    }

    @Override
    public Map<String, String> mapForControlHit(){
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Вкл","Y");
        map.put("Выкл","N");

        return map;
    }

    @Override
    public Map<String, String> mapForMessError(){
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Вкл","Y");
        map.put("Выкл","N");

        return map;
    }


}