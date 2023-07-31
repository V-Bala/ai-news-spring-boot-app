package com.yourdomain.chatbot;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;


/**
 * https://openai.com/pricing
 * Is the ChatGPT API included in the ChatGPT Plus subscription?
 * No, the ChatGPT API and ChatGPT Plus subscription are billed separately. 
 * The API has its own pricing, which can be found at https://openai.com/pricing. 
 * The ChatGPT Plus subscription covers usage on chat.openai.com only and costs $20/month.
 */

@Service
public class OpenAIService {
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = "";

    public String generateResponse(ArticleInfo post) 
    {
        RestTemplate restTemplate = new RestTemplate();

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(API_KEY);

        // Create request body
        String prompt =  post.getTitle();// + " " + post.getBody();
        ObjectMapper mapper = new ObjectMapper();
        String body;
        try 
        {
            Map<String, Object> requestBody = new HashMap<>();
            List<Map<String, String>> messages = new ArrayList<>();
            Map<String, String> message = new HashMap<>();
            message.put("role", "user");
            message.put("content", "You are an expert writer and blogger. Your task is to read the provided text which is a title of a tech news article and then write an eye-catching paragraph that entertains and informs readers:" + prompt);
            messages.add(message);
            requestBody.put("messages", messages);
            requestBody.put("model", "text-davinci-003");
            body = mapper.writeValueAsString(requestBody);
        } 
        catch (JsonProcessingException e) 
        {
            throw new RuntimeException("Failed to create request body", e);
        }

        // Create the request
        HttpEntity<String> request = new HttpEntity<>(body, headers);

        // Send the request and get the response
        ResponseEntity<String> response = restTemplate.postForEntity(API_URL, request, String.class);
        if (response != null && response.getStatusCode() == HttpStatus.OK) 
        {
            // Parse the response body here and return the generated text
            // This depends on the structure of the response body
            if (response.getBody() != null)
            {
                String responseBody = response.getBody();
                String generatedText = responseBody.split("\"text\":")[1].split(",\"")[0];
                return generatedText;
            }
        }
        else 
        {
            throw new RuntimeException("Failed to generate text: " + response.getStatusCode());
        }

        return "Reached end of function with no OpenAI call.";
    }
}
