package com.example.cryptocurrency.currency.price;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.cryptocurrency.currency.price.CurrencyPrice;
import com.example.cryptocurrency.currency.price.CurrencyPriceRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyPriceRepositoryTests {

	@Autowired
    private CurrencyPriceRepository currencyPriceRepository;
	

	@Test
    public void repositoryShouldReturnData() {
		List<CurrencyPrice> currencyPriceList = currencyPriceRepository.findAll();
  
        assertNotNull(currencyPriceList);
        assertTrue(currencyPriceList.size() > 0);
    }	

	
	@Test
	public void repositoryShouldReturnDailyCurrencyData() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");        
        LocalDate localDate = LocalDate.parse("20180507", formatter);
        
		List<CurrencyPrice> currencyPriceList = currencyPriceRepository.findByCurrencyAndDateOrderByTimeAsc("BTC", localDate);
        assertNotNull(currencyPriceList);
        assertTrue(currencyPriceList.size() > 0);
	}	
}

