import { Component, OnInit, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Stream } from '../stream';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-stream',
  templateUrl: './stream.component.html',
  styleUrls: ['./stream.component.css']
})
export class StreamComponent implements OnInit {

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.getStreams(this.itemId);
  }

  private streams: Stream[];
  private serverUrl = "http://localhost:8084/";
  @Input() itemId: string; 
  //KOMPONENTE FILMOVA SERIJA ITD CE BITI PARENT KOMPONENTE STREAM KOMPONENTI NPR [itemId]="parentVarijablaItemId"

  getStreams(itemId: string) {
    this.http.get<Stream[]>(this.serverUrl + "streams/" + itemId, {observe: 'response'}).subscribe(res => {
      if(res.status !== 200) {
        console.log(res);
        return;
      } else {
        this.streams = res.body;
      }
    })
  }

}
