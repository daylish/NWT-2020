import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotFoundComponent } from './not-found/not-found.component';
import {PagesRoutingModule} from './pages-routing.module';
import {PagesComponent} from './pages.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import {NbActionsModule, NbContextMenuModule, NbIconModule, NbMenuModule, NbUserModule} from '@nebular/theme';
import {ThemeModule} from '../@theme/theme.module';



@NgModule({
  declarations: [NotFoundComponent, PagesComponent, NotFoundComponent, DashboardComponent],
  imports: [
    CommonModule,
    PagesRoutingModule,
    NbActionsModule,
    NbIconModule,
    NbUserModule,
    NbContextMenuModule,
    ThemeModule,
    NbMenuModule,
  ]
})
export class PagesModule { }
