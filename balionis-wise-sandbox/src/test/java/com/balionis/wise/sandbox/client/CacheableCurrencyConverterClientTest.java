package com.balionis.wise.sandbox.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.balionis.wise.sandbox.model.Currency;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 */
@ExtendWith(MockitoExtension.class)
public class CacheableCurrencyConverterClientTest {

    @Test
    public void testSuccessNoCache() {

        var client = mock(CurrencyConverterClient.class);
        var rate1 = BigDecimal.valueOf(2.0).setScale(8, RoundingMode.FLOOR);
        var rate2 = BigDecimal.valueOf(2.1).setScale(8, RoundingMode.FLOOR);
        when(client.getRate(Currency.EUR, Currency.GBP)).thenReturn(rate1).thenReturn(rate2);

        var target = new CacheableCurrencyConverterClient(client,   0);

        var actual1 = target.getRate(Currency.EUR, Currency.GBP);
        assertEquals(rate1, actual1);

        var actual2 = target.getRate(Currency.EUR, Currency.GBP);
        assertEquals(rate2, actual2);

        verify(client, times(2)).getRate(Currency.EUR, Currency.GBP);
    }

    @Test
    public void testSuccessCached() {

        var client = mock(CurrencyConverterClient.class);
        var rate1 = BigDecimal.valueOf(2.0).setScale(8, RoundingMode.FLOOR);
        when(client.getRate(Currency.EUR, Currency.GBP)).thenReturn(rate1);
        var rate2 = BigDecimal.valueOf(2.1).setScale(8, RoundingMode.FLOOR);
        when(client.getRate(Currency.EUR, Currency.GBP)).thenReturn(rate1);

        var target = new CacheableCurrencyConverterClient(client,   10);

        var actual1 = target.getRate(Currency.EUR, Currency.GBP);
        assertEquals(rate1, actual1);

        var actual2 = target.getRate(Currency.EUR, Currency.GBP);
        assertEquals(rate1, actual2);

        verify(client).getRate(Currency.EUR, Currency.GBP);
    }

}
