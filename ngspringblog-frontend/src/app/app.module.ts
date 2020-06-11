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
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import {NgxWebstorageModule} from 'ngx-webstorage';
import { HomeComponent } from './home/home.component';
import { AddPostComponent } from './add-post/add-post.component';
import { from } from 'rxjs';
import { HttpClientInterceptor } from './http-client-interceptor';
import { PostComponent } from './post/post.component';
import { AuthGuard } from './auth.guard';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    RegisterComponent,
    LoginComponent,
    RegisterSuccesComponent,
    HomeComponent,
    AddPostComponent,
    PostComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgxWebstorageModule.forRoot(),
    RouterModule.forRoot([
      {path : '', component:HomeComponent},
      {path : 'register', component:RegisterComponent},
      {path : 'post/:id', component:PostComponent},
      {path:'login' ,component:LoginComponent},
      {path:'register-success' ,component:RegisterSuccesComponent},
      {path:'home' ,component:HomeComponent},
      {path:'add-post' ,component:AddPostComponent, canActivate:[AuthGuard]}

    ]),
    HttpClientModule,
    EditorModule
    
  ],

  providers: [{provide:HTTP_INTERCEPTORS,useClass:HttpClientInterceptor,multi:true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
