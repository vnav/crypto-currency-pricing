package com.example.cryptocurrency.currency.price;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.cryptocurrency.currency.price.model.Quote;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyPriceServiceTests {

	@Autowired
    private CurrencyPriceService currencyPriceService;
	

	@Test
    public void repositoryShouldDistinctCurrencies() {
		Set<String> currencyPriceSet = currencyPriceService.findDistinctCurrencies();
  
        assertNotNull(currencyPriceSet);
        assertTrue(currencyPriceSet.size() > 0);
        assertTrue(currencyPriceSet.contains("BTC"));
        assertTrue(currencyPriceSet.contains("ETC"));
        assertTrue(currencyPriceSet.contains("LTC"));
    }	

	
	@Test
	public void repositoryShouldReturnDistinctDatesByCurrency() {
		Set<LocalDate> dates = currencyPriceService.findDistinctDateByCurrency("BTC");
					
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");        
        LocalDate localDate1 = LocalDate.parse("20180507", formatter);
        LocalDate localDate2 = LocalDate.parse("20180508", formatter);
        
        assertNotNull(dates);
        assertTrue(dates.contains(localDate1));
        assertTrue(dates.contains(localDate2));
	}
	
	
	@Test
	public void repositoryShouldReturnPrincipalQuote() {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
		
        LocalDate localDate = LocalDate.parse("20180507", dateFormatter);
        BigDecimal error = new BigDecimal(0.01);
        
		Quote quote = currencyPriceService.getPrincipalQuote("BTC", localDate);
		assertEquals(quote.getCurrency(), "BTC");
		assertEquals(quote.getDate(), localDate);
		assertEquals(quote.getBuyTime(), LocalTime.parse("0915", timeFormatter));		
		assertThat(quote.getBuyPrice(), is(closeTo(new BigDecimal(34.98), error)));
		assertEquals(quote.getSellTime(), LocalTime.parse("1230", timeFormatter));
		assertThat(quote.getSellPrice(), is(closeTo(new BigDecimal(37.01), error)));
		assertThat(quote.getProfit(), is(closeTo(new BigDecimal(2.03), error)));
		
		quote = currencyPriceService.getPrincipalQuote("ETC", localDate);
		assertEquals(quote.getCurrency(), "ETC");
		assertEquals(quote.getDate(), localDate);
		assertEquals(quote.getBuyTime(), LocalTime.parse("0900", timeFormatter));		
		assertThat(quote.getBuyPrice(), is(closeTo(new BigDecimal(1.45), error)));
		assertEquals(quote.getSellTime(), LocalTime.parse("1700", timeFormatter));
		assertThat(quote.getSellPrice(), is(closeTo(new BigDecimal(2.15), error)));
		assertThat(quote.getProfit(), is(closeTo(new BigDecimal(0.7), error)));		

		quote = currencyPriceService.getPrincipalQuote("LTC", localDate);
		assertEquals(quote.getCurrency(), "LTC");
		assertEquals(quote.getDate(), localDate);
		assertEquals(quote.getBuyTime(), LocalTime.parse("0930", timeFormatter));		
		assertThat(quote.getBuyPrice(), is(closeTo(new BigDecimal(14.32), error)));
		assertEquals(quote.getSellTime(), LocalTime.parse("1245", timeFormatter));
		assertThat(quote.getSellPrice(), is(closeTo(new BigDecimal(15.03), error)));
		assertThat(quote.getProfit(), is(closeTo(new BigDecimal(0.71), error)));				
	}
	
}
