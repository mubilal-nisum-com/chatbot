package com.example.chatbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ChatController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/chat")
    public Map<String, String> chat(@RequestBody Map<String, String> request) {
        String prompt = request.get("prompt");
        String response = callLLaMAAPI(prompt);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("response", response);
        return responseBody;
    }

    private String callLLaMAAPI(String prompt) {
        String apiUrl = "http://localhost:11434/api/generate"; // Updated LLaMA API URL
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "llama3.2");
        requestBody.put("prompt", prompt);
        requestBody.put("stream", false);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, Map.class);

        Map<String, Object> responseBody = response.getBody();
        if (responseBody != null && responseBody.containsKey("response")) {
            return (String) responseBody.get("response");
        }
        return "No response from LLaMA API";
    }
}