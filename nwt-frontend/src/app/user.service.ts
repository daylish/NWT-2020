import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { User } from './user';
import {List, ListItem} from './list';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersUrl = 'http://localhost:8081/users';  // URL to web api
  private listsUrl = 'http://localhost:8083/lists';  // URL to web api
  httpOptions = {
     headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' })
  };

  constructor(private http: HttpClient) { }

  /** GET users from the server */
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl);
  }

  getLists(): Observable<List[]> {
    return this.http.get<List[]>(this.listsUrl);
  }

  createList(name: string): Observable<any> {
    const body: List = {
      userID: 1, // todo
      listID: null,
      title: name,
      itemsList: []
    };
    return this.http.post<any>(this.listsUrl + '/new', body);
  }

  createListItem(listID: number, name: string) {
    const body: ListItem = {
      listItemStatus: name,
      itemId: 1, // todo ?
      listItemID: null
    };
    return this.http.post<any>(this.listsUrl + '/' + listID + '/new', body);
  }
}
