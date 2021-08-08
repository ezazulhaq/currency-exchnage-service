package com.haa.currencyexchnageservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuteBreakerController {

    @GetMapping("/sample-api")
    public String sampleApi() {
        return "Sample API";
    }

}
