import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

import { PagesComponent } from './pages.component';
import {NotFoundComponent} from './not-found/not-found.component';
import {DashboardComponent} from "./dashboard/dashboard.component";
import {UserDisplayComponent} from '../user-display/user-display.component';

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
      path: 'user-list',
      component: UserDisplayComponent,
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
