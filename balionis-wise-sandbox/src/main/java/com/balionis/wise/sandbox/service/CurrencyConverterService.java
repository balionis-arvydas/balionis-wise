package com.balionis.wise.sandbox.service;

import com.balionis.wise.sandbox.model.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CurrencyConverterService {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyConverterService.class);

    public BigDecimal convert(Currency fromCcy, Currency toCcy, BigDecimal amount) {
        logger.info("fromCcy={}, toCcy={}, amount", fromCcy, toCcy, amount);
        return amount;
    }
}
