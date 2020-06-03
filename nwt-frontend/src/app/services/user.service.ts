import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../user';
import {List, ListItem} from '../list';
import {Config} from './config';
import {flatMap, map} from 'rxjs/operators';
import {of as observableOf, pipe} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url = Config.basepath + '/user-microservice';
  private loginUrl = Config.loginUrl;


  httpOptions = {
     headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' })
  };

  constructor(private http: HttpClient) { }

  /* GET users from the server */
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.url);
  }

  postUser(userFormData: string): Observable<User> {
    return this.http.post<User>(this.url + '/new', userFormData);
  }

  deleteUser(userID: number): Observable<User> {
    return this.http.delete<User>(this.url + '/delete/' + userID);
  }



  private setCurrentUser(user: LoggedUser) {
    localStorage.setItem('currentUser', JSON.stringify(user));
  }

  getLoggedUser(): LoggedUser {
    return JSON.parse(localStorage.getItem('currentUser'));
  }

  login(user: string, pass: string) {
    return this.http.post<LoggedUser>(this.loginUrl, {
      username: user,
      password: pass
    }, {
      headers: {
        Authorization: 'Basic ' + Config.apiKey
      }
    }).pipe(
      flatMap(token => {
        console.log('have token ', token);
        // todo get user
        return observableOf({
          token: token,
          user: null,
        });
      })
    );
  }
}

export interface Token {
  token: string;
}

export interface LoggedUser {
  token: string;
  user: User;
}
