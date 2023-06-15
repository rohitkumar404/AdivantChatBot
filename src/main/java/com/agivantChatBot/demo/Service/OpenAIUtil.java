package com.agivantChatBot.demo.Service;

import com.theokanning.openai.OpenAiApi;
import com.theokanning.openai.OpenAiHttpException;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

public class OpenAIUtil {

    @Autowired
    private OpenAiApi openAI;
    private static final String API_KEY = "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";
    private static final String MODEL_ENGINE = "text-davinci-002";

    public static String runLLM(String queryText) {
        try {
            // Set up the OpenAI API client
            OpenAiService openAiService = new OpenAiService("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");
           // CompletionRequest completionRequest = CompletionRequest.builder().build()

            // Create a GPT-3 instance
            CompletionRequest completionRequest = CompletionRequest.builder()
                    .model(MODEL_ENGINE)
                    .prompt("Generate an SQL query to " + queryText)
                    .maxTokens(1024)
                    .n(1)
                    .temperature(0.7)
                    .build();

            CompletionResult completionResult = openAiService.createCompletion(completionRequest);
            String query = completionResult.getChoices().get(0).getText().trim();
            query = query.replace("\n", " ");

            return query;
        } catch (OpenAiHttpException e) {
            e.printStackTrace();
            // Handle the exception
            return null;
        }
    }
}

