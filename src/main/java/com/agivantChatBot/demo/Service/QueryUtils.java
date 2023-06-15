package com.agivantChatBot.demo.Service;

import java.util.*;
import java.util.stream.Collectors;

public class QueryUtils {

    private static final List<String> GROUP_BY_LIST = List.of("CallCenter", "Channel", "City", "Customer", "Id", "Reason", "ResponseTime", "Sentiment", "State");
    private static final List<String> OPERATION_LIST = List.of("maximum", "minimum", "average", "count");
    private static final List<String> CALCULATION_LIST = List.of("Total");
    private static final List<String> DATA_LIST = List.of("AvgCallDuration", "AvgCustomerSatisfaction", "CallDuration", "CsatScore", "SatRate", "TotalCallsReceived");

    public static String getGroupByValue(Map<String,String> originalValueTagMap) {
        return getKeyFromList(originalValueTagMap, GROUP_BY_LIST);
    }

    public static String getOperationValue(Map<String,String> originalValueTagMap) {
        return getKeyFromList(originalValueTagMap, OPERATION_LIST);
    }

    public static String getCalculationValue(Map<String,String> originalValueTagMap) {
        return getKeyFromList(originalValueTagMap, CALCULATION_LIST);
    }

    public static String getDataValue(Map<String,String> originalValueTagMap) {
        return getKeyFromList(originalValueTagMap, DATA_LIST);
    }

    private static String getKeyFromList(Map<String,String> originalValueTagMap, List<String> keyList) {
        List<String> items = originalValueTagMap.keySet().stream().toList();
        Optional<String> matchedKey = items.stream()
                .filter(item -> keyList.stream().anyMatch(item::equalsIgnoreCase))
                .findFirst();




        return matchedKey.isPresent() ? originalValueTagMap.get(matchedKey.get()) : "";
    }

}

