package com.computacao.nuvem.peoplemicroservice.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.urls")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlConfig {
    String titleMicroService;
}
