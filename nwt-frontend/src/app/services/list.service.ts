import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {List, ListItem} from '../list';
import {Config} from "./config";

@Injectable({
  providedIn: 'root'
})
export class ListService {

  private listsUrl = Config.basepath + '/list-microservice';

  constructor(private http: HttpClient) { }


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
