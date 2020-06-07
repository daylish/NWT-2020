import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Config} from "./config";
import {UserService} from './user.service';

@Injectable({
  providedIn: 'root'
})
export class StreamService {

  private url = Config.basepath + '/stream-microservice';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' })
  };

  constructor(private http: HttpClient, private userService: UserService) { }

}
