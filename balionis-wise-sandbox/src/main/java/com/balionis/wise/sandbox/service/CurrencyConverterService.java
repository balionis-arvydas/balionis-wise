package com.balionis.wise.sandbox.service;

import com.balionis.wise.sandbox.client.CacheableCurrencyConverterClient;
import com.balionis.wise.sandbox.client.CurrencyConverterClient;
import com.balionis.wise.sandbox.model.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CurrencyConverterService {

    private final int SCALE = 2;

    private static final Logger logger = LoggerFactory.getLogger(CurrencyConverterService.class);

    private final CurrencyConverterClient currencyConverterClient;

    public CurrencyConverterService(CurrencyConverterClient currencyConverterClient) {
        this.currencyConverterClient = currencyConverterClient;
    }

    public BigDecimal convert(Currency fromCcy, Currency toCcy, BigDecimal fromAmount) {
        logger.info("fromCcy={}, toCcy={}, fromAmount={}", fromCcy, toCcy, fromAmount);
        BigDecimal rate = currencyConverterClient.getRate(fromCcy, toCcy);
        BigDecimal toAmount = fromAmount.multiply(rate).setScale(SCALE, RoundingMode.FLOOR);
        return toAmount;
    }
}
