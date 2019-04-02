import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Quote } from './models/quote';

@Injectable()
export class HistoricalService {
  constructor (
    private httpClient: HttpClient
  ) {
  }

  getCurrencies(): Observable<string[]> {
    return this.httpClient.get(`${environment.pricesServiceUrl}/currencies`).pipe(map(response => <string[]> response));
  }

  getDatesByCurrency(currency: string): Observable<string[]> {
    return this.httpClient.get(`${environment.pricesServiceUrl}/${currency}/dates`).pipe(map(response => <string[]> response));
  }

  getPrincipalQuote(currency: string, date: string): Observable<Quote> {
    return this.httpClient.get(`${environment.pricesServiceUrl}/${currency}/${date}/principal`).pipe(map(response => <Quote> response));
  }
}
