package com.balionis.wise.sandbox.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.balionis.wise.sandbox.model.Currency;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

/**
 */
@ExtendWith(MockitoExtension.class)
public class CurrencyConverterServiceTest {

    @Test
    public void testMe() {

        var service = new CurrencyConverterService();

        var expected = BigDecimal.valueOf(0.0).setScale(2);

        var actual = service.convert(Currency.EUR, Currency.GBP, expected);

        assertEquals(expected, actual);
    }
}
