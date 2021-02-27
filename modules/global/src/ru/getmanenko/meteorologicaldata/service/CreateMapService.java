package ru.getmanenko.meteorologicaldata.service;

import java.util.List;
import java.util.Map;

public interface CreateMapService {
    String NAME = "meteorologicaldata_CreateMapService";
    Map<String, Integer> mapForWind();
    Map<String, String> mapForUtit();
    Map<String, String> mapForFormatWind();
    Map<String, Integer> sampling();
    Map<String, String> mapForPa ();
    Map<String, String> mapForTemp();
    Map<String, String> mapForUnitWet();
    Map<String, String> mapForUnitKick();
    Map<String, String> mapForModeSend();
    Map<String, String> mapForResetCount();
    Map<String, String> mapForControlHit();
    Map<String, String> mapForMessError();
    List<String> listVector();
}