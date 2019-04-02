package com.example.cryptocurrency.currency.price;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.cryptocurrency.currency.price.model.Quote;

@RestController
@RequestMapping(path = "/historical", produces = APPLICATION_JSON_VALUE)
@ResponseStatus(OK)
public class CurrencyPriceController {
	private static final String DATE_PATTERN = "yyyyMMdd";
	private CurrencyPriceService currencyPriceService;
	
	@Autowired
	public CurrencyPriceController(CurrencyPriceService currencyPriceService) {
		this.currencyPriceService = currencyPriceService;
	}	
	
	@GetMapping("/all")
	@CrossOrigin(origins = "http://localhost:4200")
    public List<CurrencyPrice> findAll() {
		return currencyPriceService.findAll();
    }
	
	@GetMapping("/{currency}/{date}")
	@CrossOrigin(origins = "http://localhost:4200")
    public List<CurrencyPrice> findByCurrencyAndDate(@PathVariable("currency") String currency, @PathVariable("date") @DateTimeFormat(pattern = DATE_PATTERN) LocalDate date) {
		try {
			currency = currency.toUpperCase();
			return currencyPriceService.findByCurrencyAndDate(currency, date);
		} catch (Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, format("Currency %s with date %date does not exists", currency, date), e);
		}
    }
		
	@GetMapping("/currencies")
	@CrossOrigin(origins = "http://localhost:4200")
    public Set<String> findDistinctCurrencies() {		
		return currencyPriceService.findDistinctCurrencies();
    }	

	@GetMapping("/{currency}/dates")
	@CrossOrigin(origins = "http://localhost:4200")
    public Set<LocalDate> findDistinctDateByCurrency(@PathVariable("currency") String currency) {
		try {
			currency = currency.toUpperCase();
			return currencyPriceService.findDistinctDateByCurrency(currency);
		} catch (Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, format("Currency %s does not have data", currency), e);
		}
    }
	
	@GetMapping("/{currency}/{date}/principal")
	@CrossOrigin(origins = "http://localhost:4200")
    public Quote getPrincipalQuote(@PathVariable("currency") String currency, @PathVariable("date") @DateTimeFormat(pattern = DATE_PATTERN) LocalDate date) {
		try {
			currency = currency.toUpperCase();
			return currencyPriceService.getPrincipalQuote(currency, date);
		} catch (Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, format("Currency %s with date %date does not exists", currency, date), e);
		}
    }	
	
}
