import { Injectable } from '@angular/core';

const TOKEN = "token";
const USERNAME = "username";

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }

  static saveToken(token: string){
    window.localStorage.removeItem(TOKEN);
    window.localStorage.setItem(TOKEN, token);
  }

  static saveUserName(username: string){
    window.localStorage.removeItem(USERNAME);
    window.localStorage.setItem(USERNAME, username);
  }
}
