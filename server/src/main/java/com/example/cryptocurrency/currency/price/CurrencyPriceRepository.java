package com.example.cryptocurrency.currency.price;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyPriceRepository extends CrudRepository<CurrencyPrice, Long> {
	
	List<CurrencyPrice> findAll();
			
	List<CurrencyPrice> findByCurrencyAndDateOrderByTimeAsc(String currency, LocalDate date);
}
