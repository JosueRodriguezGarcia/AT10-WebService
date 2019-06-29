package com.fundation.webservice;

import com.fundation.webservice.controller.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class Client {

    public static void main(String[] args) {
        SpringApplication.run(Client.class, args);
    }
}
