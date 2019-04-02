package com.example.cryptocurrency.currency.price.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class Quote {
	private LocalDate date;
	private LocalTime buyTime;
	private BigDecimal buyPrice;
	private LocalTime sellTime;
	private BigDecimal sellPrice;
	private String currency;
	private BigDecimal profit;
}
