package com.openclassrooms.webapp;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.openclassrooms.webapp")
public class CustomProperties {

    private String apiUrl;  //propriété API spécifiée dans application.properties

}
