import { Component, OnInit } from '@angular/core';
import {List} from '../list';
import {UserService} from '../user.service';

@Component({
  selector: 'app-list-display',
  templateUrl: './list-display.component.html',
  styleUrls: ['./list-display.component.css']
})
export class ListDisplayComponent implements OnInit {
  data: List[] = [];

  constructor(private userService: UserService) {
    this.reload();
  }

  ngOnInit() {
  }

  reload() {
    this.userService.getLists().subscribe(data => this.data = data );
  }

  addNew() {
    const name = window.prompt('Naziv liste?');
    if (!name || name.length < 3) {
      window.alert('Los naziv');
      return;
    }
    this.userService.createList(name).subscribe(() => this.reload(), console.error);
  }

  addNewStatus(list: List) {
    const name = window.prompt('Status?');
    if (!name || name.length < 3) {
      window.alert('Los naziv');
      return;
    }
    this.userService.createListItem(list.listID, name).subscribe(() => this.reload(), console.error);
  }
}
