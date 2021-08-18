package com.haa.currencyexchnageservice.controller;

//import java.math.BigDecimal;

import com.haa.currencyexchnageservice.bean.CurrencyExchange;
import com.haa.currencyexchnageservice.exception.CurrencyNotFoundException;
import com.haa.currencyexchnageservice.repository.CurrencyExchangeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    private CurrencyExchangeRepository repository;

    @Autowired
    private Environment environment;

    @GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange fetchCurrencyExchange(@PathVariable String from, @PathVariable String to) {

        logger.info("Currency exchange called from {} to {}", from, to);

        // CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to,
        // BigDecimal.valueOf(50));
        CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
        if (currencyExchange == null)
            throw new CurrencyNotFoundException("Unable to Find data for " + from + " to " + to);

        String port = environment.getProperty("local.server.port");
        String host = environment.getProperty("HOSTNAME");

        currencyExchange.setEnvironment(port + " " + host);

        return currencyExchange;
    }
}
