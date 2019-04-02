package com.example.cryptocurrency.currency.price;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "historical_prices")
public class CurrencyPrice {
	private @Id @GeneratedValue Long id;
	private String currency;
	private LocalDate date;
	private LocalTime time;
	private BigDecimal price;
}
