package com.balionis.wise.sandbox.client;

import com.balionis.wise.sandbox.model.CacheableRate;
import com.balionis.wise.sandbox.model.Currency;
import com.balionis.wise.sandbox.model.CurrencyPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CacheableCurrencyConverterClient implements CurrencyConverterClient {

    public static final int SCALE = 8;

    public static final int EXPIRE_SECONDS = 10;

    private static final Logger logger = LoggerFactory.getLogger(CacheableCurrencyConverterClient.class);

    private Map<CurrencyPair, CacheableRate> cache = new HashMap<>();

    private final CurrencyConverterClient client;

    private final int expireSeconds;

    public CacheableCurrencyConverterClient(CurrencyConverterClient client, int expireSeconds) {
        cache = buildCache();
        this.client = client;
        this.expireSeconds = expireSeconds;
    }

    @Override
    public BigDecimal getRate(Currency fromCurrency, Currency toCurrency) {
        logger.info("fromCurrency={}, toCurrency={}", fromCurrency, toCurrency);
        CurrencyPair currencyPair = new CurrencyPair(fromCurrency, toCurrency);
        CacheableRate rate = cache.get(currencyPair);
        if (rate == null) {
            throw new IllegalArgumentException("unsupported currency pair " + currencyPair);
        }
        LocalDateTime expireAfter = LocalDateTime.now().minusSeconds(expireSeconds);
        if (!rate.isExpired(expireAfter)) {
            logger.info("rate={}", rate);
            return rate.getRate();
        } else {
            synchronized (rate) {
                BigDecimal newRate = client.getRate(fromCurrency, toCurrency);
                rate.setRate(newRate.setScale(SCALE, RoundingMode.FLOOR));
                logger.info("updated rate={}", rate);
                return rate.getRate();
            }
        }
    }

    private Map<CurrencyPair, CacheableRate> buildCache() {
        Map<CurrencyPair, CacheableRate> cache = new HashMap<>();

        Currency[] currencies = Currency.values();
        for (Currency fromCurrency : currencies) {
            for (Currency toCurrency : currencies) {
                cache.put(new CurrencyPair(fromCurrency, toCurrency),
                        new CacheableRate(BigDecimal.valueOf(0.0).setScale(SCALE, RoundingMode.FLOOR),
                                LocalDateTime.MIN));
            }
        }
        return cache;
    }
}
