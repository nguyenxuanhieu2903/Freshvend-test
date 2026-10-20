package com.freshvend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HealthController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("service", "Freshvend-test");
        response.put("status", "UP");

        Map<String, String> dbInfo = new LinkedHashMap<>();
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData meta = conn.getMetaData();
            dbInfo.put("status", "UP");
            dbInfo.put("databaseProduct", meta.getDatabaseProductName());
            dbInfo.put("databaseVersion", meta.getDatabaseProductVersion());
        } catch (Exception e) {
            dbInfo.put("status", "DOWN");
            dbInfo.put("error", e.getMessage());
        }
        response.put("database", dbInfo);

        return ResponseEntity.ok(response);
    }
}
