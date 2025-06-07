package com.balionis.wise.sandbox.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.balionis.wise.sandbox.client.CurrencyConverterClient;
import com.balionis.wise.sandbox.model.Currency;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 */
@ExtendWith(MockitoExtension.class)
public class CurrencyConverterServiceTest {

    @Test
    public void testSuccess() {

        var client = mock(CurrencyConverterClient.class);
        var rate = BigDecimal.valueOf(2.0).setScale(8, RoundingMode.FLOOR);
        when(client.getRate(Currency.EUR, Currency.GBP)).thenReturn(rate);

        var service = new CurrencyConverterService(client);

        var amount = BigDecimal.valueOf(10.0).setScale(2, RoundingMode.FLOOR);

        var actual = service.convert(Currency.EUR, Currency.GBP, amount);

        var expected = BigDecimal.valueOf(20.0).setScale(2, RoundingMode.FLOOR);

        assertEquals(expected, actual);
    }
}
