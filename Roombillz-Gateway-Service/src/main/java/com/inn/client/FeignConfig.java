package com.inn.client;


import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;

import feign.Logger;

@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
    
    @Bean
    public HttpMessageConverters messageConverters() {
        return new HttpMessageConverters(new SourceHttpMessageConverter());
    }
}