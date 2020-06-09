import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {EditorModule} from '@tinymce/tinymce-angular';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { RegisterComponent } from './auth/register/register.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterSuccesComponent } from './auth/register-succes/register-succes.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms'
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import {NgxWebstorageModule} from 'ngx-webstorage';
import { HomeComponent } from './home/home.component';
import { AddPostComponent } from './add-post/add-post.component';
import { from } from 'rxjs';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    RegisterComponent,
    LoginComponent,
    RegisterSuccesComponent,
    HomeComponent,
    AddPostComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgxWebstorageModule.forRoot(),
    RouterModule.forRoot([
      {path : 'register', component:RegisterComponent},
      {path:'login' ,component:LoginComponent},
      {path:'register-success' ,component:RegisterSuccesComponent},
      {path:'home' ,component:HomeComponent},
      {path:'add-post' ,component:AddPostComponent}

    ]),
    HttpClientModule,
    EditorModule
    
  ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
