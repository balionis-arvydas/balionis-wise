package com.balionis.wise.sandbox.client;

import com.balionis.wise.sandbox.model.Currency;

import java.math.BigDecimal;

public interface CurrencyConverterClient {
    BigDecimal getRate(Currency fromCurrency, Currency toCurrency);
}
