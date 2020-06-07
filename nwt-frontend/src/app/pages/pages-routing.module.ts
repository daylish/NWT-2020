import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

import { PagesComponent } from './pages.component';
import {NotFoundComponent} from './not-found/not-found.component';
import {DashboardComponent} from "./dashboard/dashboard.component";
import {UserDisplayComponent} from '../user-display/user-display.component';
import {UserInputComponent} from './user-input/user-input.component';
import {UserListComponent} from './user-list/user-list.component';
import {MoviesInputComponent} from './movies-input/movies-input.component';
import {MoviesListComponent} from './movies-list/movies-list.component';
import {ShowListComponent} from './show-list/show-list.component';
import {ShowInputComponent} from './show-input/show-input.component';

const routes: Routes = [{
  path: '',
  component: PagesComponent,
  children: [

    {
      path: '',
      redirectTo: 'dashboard',
      pathMatch: 'full',
    },
    {
      path: 'dashboard',
      component: DashboardComponent,
      pathMatch: 'full',
    },



    {
      path: 'user',
      component: UserListComponent,
      pathMatch: 'full',
    },
    {
      path: 'user/create',
      component: UserInputComponent,
      pathMatch: 'full',
    },
    {
      path: 'user/:id',
      component: UserInputComponent,
      pathMatch: 'full',
    },



    {
      path: 'movie',
      component: MoviesListComponent,
      pathMatch: 'full',
    },
    {
      path: 'movie/create',
      component: MoviesInputComponent,
      pathMatch: 'full',
    },
    {
      path: 'movie/:id',
      component: MoviesInputComponent,
      pathMatch: 'full',
    },



    {
      path: 'show',
      component: ShowListComponent,
      pathMatch: 'full',
    },
    {
      path: 'show/create',
      component: ShowInputComponent,
      pathMatch: 'full',
    },
    {
      path: 'show/:id',
      component: ShowInputComponent,
      pathMatch: 'full',
    },


    {
      path: '**',
      component: NotFoundComponent,
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PagesRoutingModule {
}
