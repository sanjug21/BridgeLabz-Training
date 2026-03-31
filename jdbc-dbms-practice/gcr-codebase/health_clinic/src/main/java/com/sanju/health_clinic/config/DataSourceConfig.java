package com.sanju.health_clinic.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

    private static final String DB_NAME = "health_clinic_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Sanju@123";
    private static final String JDBC_PARAMS = "useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    @Bean
    public DataSource dataSource() {
        createDatabaseIfMissing();
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/" + DB_NAME + "?" + JDBC_PARAMS);
        config.setUsername(DB_USER);
        config.setPassword(DB_PASSWORD);

        return new HikariDataSource(config);
    }

    private void createDatabaseIfMissing() {
        String adminUrl = "jdbc:mysql://localhost:3306/?" + JDBC_PARAMS;
        try (Connection connection = DriverManager.getConnection(adminUrl, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement()) {
            statement.execute("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
        } catch (Exception ex) {
            throw new IllegalStateException("Failed to ensure database exists: " + DB_NAME, ex);
        }
    }
}
