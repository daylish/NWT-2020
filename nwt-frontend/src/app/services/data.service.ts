import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Config} from "./config";

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private url = Config.basepath + '/data-microservice';

  constructor(private http: HttpClient) { }
}
