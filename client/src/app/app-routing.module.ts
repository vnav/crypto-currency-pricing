import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PRICES_ROUTE } from './prices/prices.route';

const routes: Routes = [PRICES_ROUTE];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
