import {Component, OnInit, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'app-movies-list',
  templateUrl: './movies-list.component.html',
  styleUrls: ['./movies-list.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class MoviesListComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
