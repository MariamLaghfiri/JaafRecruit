import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl: string = "http://localhost:8082/auth/keycloak/token";
  private registerUrl: string = "http://localhost:8082/user/add";

  constructor(private http: HttpClient,private router: Router) { }

  register(userObj:any){
    return this.http.post<any>(this.registerUrl, userObj)
  }

  login(loginObj:any){
    return this.http.post<any>(this.loginUrl, loginObj)
  }

  storToken(tokenValue: string){
    localStorage.setItem('token', tokenValue);
  }

  logout(){
    localStorage.clear();
    this.router.navigate(['login']);
  }

  getToken(){
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean{
    return !!localStorage.getItem('token');
  }
}