package com.josiah.squirrels.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**") // apply CORS rule to all endpoints in application
                .allowedOrigins("http://localhost:5173") // allow requests from this origin
                .allowedMethods("GET","POST","PUT","DELETE","PATCH") // allow these HTTP methods
                .allowedHeaders("*") // allow all headers
                .allowCredentials(true)   // if using cookies/Authorization
                .maxAge(3600); // max age in seconds for cached responses
    }
}
