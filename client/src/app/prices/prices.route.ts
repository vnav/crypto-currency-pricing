import { Route } from '@angular/router';
import { HistoricalComponent } from './historical/historical.component';


export const PRICES_ROUTE: Route = {
    path: '',
    component: HistoricalComponent,
    data: {
        authorities: [],
        pageTitle: 'home.title'
    }
};
