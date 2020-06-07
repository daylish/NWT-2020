import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../user';
import {List, ListItem} from '../list';
import {Config} from './config';
import {flatMap, map} from 'rxjs/operators';
import {of as observableOf, pipe} from 'rxjs';
import {ServiceUtils} from './ServiceUtils';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url = Config.basepath + '/user-microservice/users';
  private loginUrl = Config.loginUrl;


  httpOptions = {
     headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' })
  };

  constructor(private http: HttpClient) { }

  /* GET users from the server */
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.url, ServiceUtils.GetHttpOptions(this));
  }

  postUser(userFormData: string): Observable<User> {
    return this.http.post<User>(this.url + '/new', userFormData, ServiceUtils.GetHttpOptions(this));
  }

  deleteUser(userID: number): Observable<User> {
    return this.http.delete<User>(this.url + '/delete/' + userID, ServiceUtils.GetHttpOptions(this));
  }

  getMe(jwt?: string): Observable<JwtInfo> {
    return this.http.get<JwtInfo>(this.url + '/me', {
      headers: {
        Authorization: jwt
      }
    });
  }



  private setCurrentUser(user: LoggedUser) {
    localStorage.setItem('currentUser', JSON.stringify(user));
  }

  getLoggedUser(): LoggedUser {
    return JSON.parse(localStorage.getItem('currentUser'));
  }

  login(user: string, pass: string) {
    return this.http.post<Token>(this.loginUrl, {
      username: user,
      password: pass
    }, {
      headers: {
        Authorization: 'Basic ' + Config.apiKey
      }
    }).pipe(
      flatMap((token: Token) => {
        return this.getMe(token.token)
          .pipe(
            map((me: JwtInfo): LoggedUser => {
              const res = {
                token: token.token,
                user: me,
              };
              this.setCurrentUser(res);
              return res;
            })
          );
      })
    );
  }
}

export interface Token {
  token: string;
}

export interface LoggedUser {
  token: string;
  user: JwtInfo;
}

export interface JwtInfo {
  sub: string;
  authorities: string[];
}
