package com.mkyong.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataConnect {
    @Bean
    public DriverManagerDataSource dataSource() {
        return new DriverManagerDataSource("jdbc:mysql://localhost:3306/araqichDB", "root", "root");
    }
}
