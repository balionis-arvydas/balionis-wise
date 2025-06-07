package com.balionis.wise.sandbox.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CacheableRate {
    private BigDecimal rate;
    private LocalDateTime lastModifiedTS;

    public CacheableRate(BigDecimal rate, LocalDateTime lastModifiedTS) {
        this.rate = rate;
        this.lastModifiedTS = lastModifiedTS;
    }

    public CacheableRate(BigDecimal rate) {
        this(rate, LocalDateTime.now());
    }

    public BigDecimal getRate() {
        return this.rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
        lastModifiedTS = LocalDateTime.now();
    }

    public boolean isExpired(LocalDateTime expiredAfterTS) {
        return lastModifiedTS.isBefore(expiredAfterTS);
    }

    @Override
    public String toString() {
        return "rate=" + rate + ", lasrModifiedTS=" + lastModifiedTS;
    }
}