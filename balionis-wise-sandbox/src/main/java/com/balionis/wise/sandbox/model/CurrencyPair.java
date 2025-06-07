package com.balionis.wise.sandbox.model;

public class CurrencyPair {
    private final Currency fromCurrency;
    private final Currency toCurrency;

    public CurrencyPair(Currency fromCurrency, Currency toCurrency) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
    }

    public Currency getFromCurrency() {
        return fromCurrency;
    }

    public Currency getToCurrency() {
        return toCurrency;
    }

    @Override
    public int hashCode() {
        return fromCurrency.hashCode() + toCurrency.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CurrencyPair that)) {
            return false;
        }
        return (that.fromCurrency == fromCurrency && that.toCurrency == toCurrency);
    }

    @Override
    public String toString() {
        return "fromCurrency=" + fromCurrency + ", toCurrency=" + toCurrency;
    }
}