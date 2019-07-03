package com.example.cryptocurrency.currency.price.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;

public class Quote {
	@Getter
	private final LocalDate date;
	@Getter
	private final LocalTime buyTime;
	@Getter
	private final BigDecimal buyPrice;
	@Getter
	private final LocalTime sellTime;
	@Getter
	private final BigDecimal sellPrice;
	@Getter
	private final String currency;
	@Getter
	private final BigDecimal profit;
	
	private Quote(Builder builder) {
		date = builder.date;
		buyTime = builder.buyTime;
		buyPrice = builder.buyPrice;
		sellTime = builder.sellTime;
		sellPrice = builder.sellPrice;
		currency = builder.currency;
		profit = builder.profit;
	}
	
	public static class Builder {
		private LocalDate date;
		private LocalTime buyTime;
		private BigDecimal buyPrice;
		private LocalTime sellTime;
		private BigDecimal sellPrice;
		private String currency;
		private BigDecimal profit;
	
		public Builder date(LocalDate date) {
			this.date = date;
			return this;
		}

		public Builder buyTime(LocalTime buyTime) {
			this.buyTime = buyTime;
			return this;
		}
		
		public Builder buyPrice(BigDecimal buyPrice) {
			this.buyPrice = buyPrice;
			return this;
		}

		public Builder sellTime(LocalTime sellTime) {
			this.sellTime = sellTime;
			return this;
		}

		public Builder sellPrice(BigDecimal sellPrice) {
			this.sellPrice = sellPrice;
			return this;
		}

		public Builder currency(String currency) {
			this.currency = currency;
			return this;
		}

		public Builder profit(BigDecimal profit) {
			this.profit = profit;
			return this;
		}

		public Quote build() {
			return new Quote(this);
		}
	}
}
