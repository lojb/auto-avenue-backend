package com.codecool.autoavenue.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<String> checkHealth() {
        // You can perform additional checks or actions here if needed
        return ResponseEntity.status(HttpStatus.OK).body("Server is healthy");
    }
}
