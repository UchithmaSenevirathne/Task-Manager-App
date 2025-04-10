import { Injectable } from '@angular/core';

const TOKEN = "token";
const USERNAME = "username";

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }

  static saveUserName(username: string){
    window.localStorage.removeItem(USERNAME);
    window.localStorage.setItem(USERNAME, username);
  }

  static saveToken(token: string){
    window.localStorage.removeItem(TOKEN);
    window.localStorage.setItem(TOKEN, token);
  }

  

  static getToken(){
    return localStorage.getItem(TOKEN);
  }

  static getUserName(){
    return localStorage.getItem(USERNAME);
  }

  static isLoggedIn(): boolean{
    if(this.getToken() === null){
      return false;
    }
    return true;
  }

  static hasToken(): boolean{
    if(this.getToken() === null){
      return false;
    }
    return true;
  }

  static signout(){
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USERNAME);
  }
}
