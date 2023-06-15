package com.agivantChatBot.demo.Service;


import com.agivantChatBot.demo.model.WebhookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QueryService {

    private final DatabaseService databaseService;

    @Autowired
    public QueryService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public String handleRequest(WebhookRequest request) {
        String response = "something went wrong";
        try {
            String intent = request.getIntentInfo().getDisplayName();

            Map<String,Map<String,String>> ParametersMap = request.getIntentInfo().getParameters();

            List<String> tags = request.getIntentInfo().getParameters().keySet().stream().toList();

            Map<String,String> originalValueTagMap= new HashMap<>();

            for (String tag:tags ) {
                originalValueTagMap.put(tag,ParametersMap.get(tag).get("resolvedValue"));
            }



            switch (intent) {
                case "GetMaxOrMinValue":
                    response = databaseService.getMaxValueIntent(originalValueTagMap);
                    break;
                case "GetAVGValueByType":
                    response = databaseService.getAvgValueByType(originalValueTagMap);
                    break;
                case "CallcenterInfo":
                    response = databaseService.getCallCenterInfo(originalValueTagMap);
                    break;
                case "ChannelInfo":
                    response = databaseService.getChannelInfo(originalValueTagMap);
                    break;
                default:
                    response = "Unknown intent";
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            return response;
        }



    }
}
