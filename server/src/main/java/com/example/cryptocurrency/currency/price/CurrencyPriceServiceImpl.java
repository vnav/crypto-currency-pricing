package com.example.cryptocurrency.currency.price;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cryptocurrency.currency.price.model.Quote;

@Service
public class CurrencyPriceServiceImpl implements CurrencyPriceService {
	
	private final CurrencyPriceRepository currencyPriceRepository;

    @Autowired
    public CurrencyPriceServiceImpl(CurrencyPriceRepository currencyPriceRepository) {
    	this.currencyPriceRepository = currencyPriceRepository;
    }
	
	@Override
	public List<CurrencyPrice> findAll() {
		return currencyPriceRepository.findAll();
	}
	
	@Override
	public List<CurrencyPrice> findByCurrencyAndDate(String currency, LocalDate date) {
		return currencyPriceRepository.findByCurrencyAndDateOrderByTimeAsc(currency, date);
	}
	
	@Override
	public Set<String> findDistinctCurrencies() {
		List<CurrencyPrice> currencyPriceList = findAll();
		
		Set<String> currencies = new HashSet<String>();
		currencyPriceList.stream().forEach(cp -> currencies.add(cp.getCurrency()));
		
		return currencies;
	}
	
	@Override
	public Set<LocalDate> findDistinctDateByCurrency(String currency) {
		Set<LocalDate> dates = new HashSet<LocalDate>();
		List<CurrencyPrice> currencyPriceList = findAll();
		currencyPriceList.stream()
			.filter(cp -> cp.getCurrency().equalsIgnoreCase(currency))
			.forEach(cp -> dates.add(cp.getDate()));
		
		return dates;
	}
	
	@Override
	public Quote getPrincipalQuote(String currency, LocalDate date) {		
		List<Quote> quoteList = getAllQuotes(currency, date);
		return getQuoteWithHighestProfit(quoteList);		
	}
	
	private List<Quote> getAllQuotes(String currency, LocalDate date) {
		List<Quote> quoteList = new ArrayList<Quote>();		
		List<CurrencyPrice> currencyPriceList = findByCurrencyAndDate(currency, date);
		
		int size = currencyPriceList.size();
		
		for (int i = 0; i < size - 1; i++) {
			CurrencyPrice start = currencyPriceList.get(i);
			for (int j = i + 1; j < size; j++) {				
				CurrencyPrice end = currencyPriceList.get(j);
				
				Quote quote = new Quote.Builder()
							.buyTime(start.getTime())
							.buyPrice(start.getPrice())
							.sellTime(end.getTime())
							.sellPrice(end.getPrice())
							.profit(end.getPrice().subtract(start.getPrice()))
							.currency(currency)
							.date(date)
							.build();
				
				quoteList.add(quote);
			}			
		}		
		return quoteList;
	}
	
	private Quote getQuoteWithHighestProfit(List<Quote> quoteList) {
		Quote result = null;
		
		for (Quote quote: quoteList) {
			if (result != null) {
				if(quote.getProfit().compareTo(result.getProfit()) > 0) {
					result = quote;
				}
			} else {
				result = quote;
			}
		};
		
		return result;
	}	
}
