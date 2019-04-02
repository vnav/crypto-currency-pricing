import { Component, OnInit, Inject, LOCALE_ID } from '@angular/core';
import { formatDate  } from '@angular/common';
import { HistoricalService } from './historical.service';
import { Quote } from './models/quote';

@Component({
  selector: 'app-historical',
  templateUrl: './historical.component.html',
  styleUrls: ['./historical.component.scss']
})
export class HistoricalComponent implements OnInit {
  currencyList: string[];
  currencyDateList: string[];
  selectedCurrency: string;
  selectedDate: string;
  quoteList: Quote[] = [];

  constructor(
    private historicalService: HistoricalService,
    @Inject(LOCALE_ID) private locale: string
  ) { }

  async ngOnInit() {
    this.currencyList = await this.historicalService.getCurrencies().toPromise();
  }

  async refreshDateList(event) {
    this.currencyDateList = await this.historicalService.getDatesByCurrency(this.selectedCurrency).toPromise();
    this.selectedDate = null;
  }

  async getPrincipalQuote() {
    if (this.selectedCurrency && this.selectedDate && !this.quoteListContains(this.selectedCurrency, this.selectedDate)) {
      const principalQuote = await this.historicalService.getPrincipalQuote(this.selectedCurrency, this.selectedDate).toPromise();
      this.quoteList.push(principalQuote);
    }
  }

  removeQuote(index: number): void {
    this.quoteList.splice(index, 1);
  }

  quoteListContains(currency: string, date: string): boolean {
    return this.quoteList.findIndex(q => q.currency === currency && formatDate (q.date, 'yyyyMMdd', this.locale) === date) > -1;
  }
}
