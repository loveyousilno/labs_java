package com.example.project1.controller;

import com.example.project1.service.PostalCodesService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostalCodesController {

    private final PostalCodesService postalCodesService;

    public PostalCodesController(PostalCodesService postalCodesService) {
        this.postalCodesService = postalCodesService;
    }

    @GetMapping("/region/{zipcode}")
    public ResponseEntity<JsonNode> getRegionByZipCode(@PathVariable String zipcode) {
        JsonNode regionData = postalCodesService.getRegionByZipCode(zipcode);
        if (regionData != null) {
            return ResponseEntity.ok(regionData);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}