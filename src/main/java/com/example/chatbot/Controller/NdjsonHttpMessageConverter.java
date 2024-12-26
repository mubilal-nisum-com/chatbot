package com.example.chatbot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NdjsonHttpMessageConverter extends AbstractHttpMessageConverter<List<Map<String, Object>>> {

    public NdjsonHttpMessageConverter() {
        super(new MediaType("application", "x-ndjson", StandardCharsets.UTF_8));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return List.class.isAssignableFrom(clazz);
    }

    @Override
    protected List<Map<String, Object>> readInternal(Class<? extends List<Map<String, Object>>> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputMessage.getBody(), StandardCharsets.UTF_8));
        List<Map<String, Object>> result = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            result.add(new ObjectMapper().readValue(line, Map.class));
        }
        return result;
    }

    @Override
    protected void writeInternal(List<Map<String, Object>> maps, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        throw new UnsupportedOperationException("Writing NDJSON is not supported");
    }
}
