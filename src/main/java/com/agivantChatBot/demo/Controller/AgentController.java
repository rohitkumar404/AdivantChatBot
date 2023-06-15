package com.agivantChatBot.demo.Controller;

import com.agivantChatBot.demo.Repositories.CallDataRepository;
import com.agivantChatBot.demo.model.FulfillmentResponse;
import com.agivantChatBot.demo.model.RequestBodyEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class AgentController {

    Logger logger = LoggerFactory.getLogger(AgentController.class);
    @Autowired CallDataRepository callDataRepository;
    @RequestMapping("/hello")
    @ResponseBody
    public ResponseEntity<FulfillmentResponse> sayHello(@RequestHeader Map<String,String> requestHeader, @RequestBody RequestBodyEntity requestBody){


        List<Object> callDataList = Collections.singletonList(callDataRepository.runDynamicQuery("select * from calldata;"));
        System.out.println(callDataList);



        Map<String,List<Map<String,Map<String,List<String>>>>> fullFillment = new HashMap<>();
        List<Map<String,Map<String,List<String>>>> messages = new ArrayList<>();
        Map<String,Map<String,List<String>>> text = new HashMap<>();
        Map<String,List<String>> innerText = new HashMap<>();

        innerText.put("text",List.of(requestBody.toString()));
        text.put("text",innerText);

        messages.add(text);
        fullFillment.put("messages",messages);
        FulfillmentResponse responseBody = new FulfillmentResponse();
        logger.error(requestBody.toString());
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
