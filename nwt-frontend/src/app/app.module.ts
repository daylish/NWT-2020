import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DataDisplayComponent } from './data-display/data-display.component';
import { UserDisplayComponent } from './user-display/user-display.component';
import { ListDisplayComponent } from './list-display/list-display.component';
import { StreamComponent } from './stream/stream.component';
import {SharedModule} from './shared/shared.module';
import { PagesComponent } from './pages/pages.component';
import {AuthGuardService} from './shared/services/authguard.service';

@NgModule({
  declarations: [
    AppComponent,
    DataDisplayComponent,
    UserDisplayComponent,
    StreamComponent,
    UserDisplayComponent,
    ListDisplayComponent,
  ],
  providers: [AuthGuardService],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    SharedModule,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
