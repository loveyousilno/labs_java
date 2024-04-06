package com.example.project1.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class PostalCodesService {
    private static String urlApi = "https://api.zippopotam.us/us/{zipcode}";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper; // ObjectMapper для работы с JSON

    public JsonNode getRegionByZipCode(String zipcode) {
        String url = urlApi.replace("{zipcode}", zipcode);

        // Выполняем GET-запрос и получаем ответ в виде строки
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String responseBody = responseEntity.getBody();

        try {
            // Преобразуем строку ответа в объект JsonNode с помощью ObjectMapper
            return objectMapper.readTree(responseBody);
        } catch (Exception e) {
            // Обрабатываем возможные ошибки парсинга (например, ошибки JSON)
            e.printStackTrace();
            return null; // Или выбрасываем исключение в зависимости от стратегии обработки ошибок
        }
    }
}