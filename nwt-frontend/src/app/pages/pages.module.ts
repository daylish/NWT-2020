import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotFoundComponent } from './not-found/not-found.component';
import {PagesRoutingModule} from './pages-routing.module';
import {PagesComponent} from './pages.component';



@NgModule({
  declarations: [NotFoundComponent, PagesComponent, NotFoundComponent],
  imports: [
    CommonModule,
    PagesRoutingModule,
  ]
})
export class PagesModule { }
