package com.github.lassulfi;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.MySQLContainer;

import java.util.HashMap;
import java.util.Map;

public class DatabaseLifecycle implements QuarkusTestResourceLifecycleManager {

    private static final MySQLContainer<?> MYSQL = new MySQLContainer<>("mysql/mysql-server:5.7");

    @Override
    public Map<String, String> start() {
        MYSQL.start();
        Map<String, String> datasourceProperties = new HashMap<>();
        datasourceProperties.put("quarkus.datasource.url", MYSQL.getJdbcUrl());
        datasourceProperties.put("quarkus.datasource.username", MYSQL.getUsername());
        datasourceProperties.put("quarkus.datasource.password", MYSQL.getPassword());
        return datasourceProperties;
    }

    @Override
    public void stop() {
        if(MYSQL != null) {
            MYSQL.stop();
        }
    }
}
