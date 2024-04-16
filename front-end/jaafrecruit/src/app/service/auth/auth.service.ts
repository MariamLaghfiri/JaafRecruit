import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl: string = "http://localhost:8082/auth/keycloak/token";
  private registerUrl: string = "http://localhost:8082/user/add";
  userId! :string; 
  constructor(private http: HttpClient, private router: Router) { }

  register(userObj: any) {
    return this.http.post<any>(this.registerUrl, userObj)
  }

  login(loginObj: any) {
    return this.http.post<any>(this.loginUrl, loginObj)
  }

  storToken(tokenValue: string) {
    localStorage.setItem('token', tokenValue);
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['login']);
  }

  getToken() {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  storUserId(userId: string) {
    localStorage.setItem('userId', userId);
  }

  getUserId() {
    return localStorage.getItem('userId');
  }

}