import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { from, Observable } from 'rxjs';
import { RegisterPayload } from './register-payload';
import { LoginPayload } from './login-payload';
import { JwtAuthResponse } from './jwt-auth-response';
import {map} from 'rxjs/operators';
import {LocalStorageService} from 'ngx-webstorage'

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url = "http://localhost:8080/api/auth/"

  constructor(private httpClient: HttpClient,private localStorageService:LocalStorageService) { }


  register(registerPayload:RegisterPayload):Observable<any>{
    return this.httpClient.post(this.url + "signup",registerPayload);
  }

  login(loginPayload:LoginPayload):Observable<boolean>{

    return this.httpClient.post<JwtAuthResponse>(this.url+"login",loginPayload).pipe(map(data => {
      this.localStorageService.store('authenticationToken',data.authenticationToken);
      this.localStorageService.store('userName',data.userName);

      return true;
      })

    );
    
  }

  isAuthenticated():Boolean{

    return this.localStorageService.retrieve('userName') != null;
  }

}
