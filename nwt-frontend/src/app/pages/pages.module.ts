import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotFoundComponent } from './not-found/not-found.component';
import {PagesRoutingModule} from './pages-routing.module';
import {PagesComponent} from './pages.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import {NbActionsModule, NbContextMenuModule, NbIconModule, NbMenuModule, NbUserModule} from '@nebular/theme';
import {ThemeModule} from '../@theme/theme.module';
import { MoviesListComponent } from './movies-list/movies-list.component';
import { MoviesInputComponent } from './movies-input/movies-input.component';
import { UserListComponent } from './user-list/user-list.component';
import { UserInputComponent } from './user-input/user-input.component';
import { ShowListComponent } from './show-list/show-list.component';
import { ShowInputComponent } from './show-input/show-input.component';
import { ReviewInputComponent } from './review-input/review-input.component';



@NgModule({
  declarations: [NotFoundComponent, PagesComponent, NotFoundComponent, DashboardComponent, MoviesListComponent, MoviesInputComponent, UserListComponent, UserInputComponent, ShowListComponent, ShowInputComponent, ReviewInputComponent],
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
