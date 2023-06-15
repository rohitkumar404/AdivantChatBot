package com.agivantChatBot.demo.Controller;

import com.agivantChatBot.demo.Service.QueryService;
import com.agivantChatBot.demo.model.*;
import com.agivantChatBot.demo.model.WebhookRequest;
import com.agivantChatBot.demo.model.WebhookResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class WebhookController {

    private final QueryService queryService;
    Logger logger = LoggerFactory.getLogger(AgentController.class);

    @Autowired
    public WebhookController(QueryService queryService) {
        this.queryService = queryService;
    }

    @PostMapping("/hello")
    public ResponseEntity<Object> handleWebhook(@RequestBody WebhookRequest request) {
        String result = queryService.handleRequest(request);
        FulfillmentResponse response = createJSONResponse(result);
        return ResponseEntity.ok(response);
    }

    public FulfillmentResponse createJSONResponse(String result) {
        FulfillmentResponse response = new FulfillmentResponse();
        Messages messages = new Messages();
        Message message = new Message();
        Text text = new Text();
        List<String> textList = new ArrayList<>();

        textList.add(result);
        text.setText(textList);
        message.setText(text);
        messages.setMessages(Collections.singletonList(message));
        response.setFulfillment_response(messages);
        return response;

//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            String jsonResponse = objectMapper.writeValueAsString(response);
//            return jsonResponse;
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return "";
//        }
    }
}
