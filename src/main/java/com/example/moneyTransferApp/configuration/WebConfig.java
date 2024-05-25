package com.example.moneyTransferApp.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "HEAD", "OPTIONS", "PUT", "PATCH", "DELETE")
                .allowCredentials(true)
                .allowedHeaders("Accept", "Access-Control-Request-Method", "Access-Control-Allow-Methods", "Access-Control-Allow-Origin", "Access-Control-Request-Headers",
                        "Accept-Language", "Authorization", "Content-Type", "Request-Name", "Request-Surname", "Origin", "X-Request-AppVersion",
                        "X-Request-OsVersion", "X-Request-Device", "X-Requested-With")
                .maxAge(3600);
    }

}