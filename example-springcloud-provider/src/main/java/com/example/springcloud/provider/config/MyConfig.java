package com.example.springcloud.provider.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
public class MyConfig {


    @ConfigurationProperties(prefix = "myconfig.datasource")
    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create().build();
    }

}
