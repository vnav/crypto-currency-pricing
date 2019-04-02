package com.example.cryptocurrency.currency.price;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.example.cryptocurrency.currency.price.model.Quote;

public interface CurrencyPriceService {
	List<CurrencyPrice> findAll();
	
	Set<String> findDistinctCurrencies();
	
	Set<LocalDate> findDistinctDateByCurrency(String currency);
	
	List<CurrencyPrice> findByCurrencyAndDate(String currency, LocalDate date);
	
	Quote getPrincipalQuote(String currency, LocalDate date);
}
