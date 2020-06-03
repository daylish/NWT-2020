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
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
  NbChatModule,
  NbDatepickerModule,
  NbDialogModule,
  NbMenuModule,
  NbSidebarModule,
  NbToastrModule,
  NbWindowModule
} from '@nebular/theme';
import {CoreModule} from './@core/core.module';
import {ThemeModule} from './@theme/theme.module';

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

    BrowserAnimationsModule,
    HttpClientModule,
    AppRoutingModule,
    NbSidebarModule.forRoot(),
    NbMenuModule.forRoot(),
    NbDatepickerModule.forRoot(),
    NbDialogModule.forRoot(),
    NbWindowModule.forRoot(),
    NbToastrModule.forRoot(),
    NbChatModule.forRoot({
      messageGoogleMapKey: 'AIzaSyA_wNuCzia92MAmdLRzmqitRGvCF7wCZPY',
    }),
    CoreModule.forRoot(),
    ThemeModule.forRoot(),
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
