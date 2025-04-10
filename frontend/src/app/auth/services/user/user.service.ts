import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getUserIdByUsername(username: string): Observable<number> {
    return this.http.get<number>(`http://localhost:8080/backend/user/id/${username}`);
  }
}
