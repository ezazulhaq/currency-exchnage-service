package com.haa.currencyexchnageservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuteBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuteBreakerController.class);

    @GetMapping("/sample-api")
    @Retry(name = "sample-api")
    public String sampleApi() {
        logger.info("Sample API call received");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8001/sample-api-dummy",
                String.class);

        return forEntity.getBody();
    }

}
