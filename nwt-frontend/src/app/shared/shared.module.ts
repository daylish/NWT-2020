import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AuthComponent} from './auth/auth.component';
import {LoginComponent} from './auth/login/login.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {BrowserModule} from '@angular/platform-browser';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,

    RouterModule,
  ],
  declarations: [

    AuthComponent,

    LoginComponent,
  ],
  exports: [
    AuthComponent,

    LoginComponent,
  ],
})
export class SharedModule { }
