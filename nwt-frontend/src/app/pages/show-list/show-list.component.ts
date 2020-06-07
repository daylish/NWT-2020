import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {LocalDataSource} from 'ng2-smart-table';
import {UserService} from '../../services/user.service';
import {DataService} from '../../services/data.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-show-list',
  templateUrl: './show-list.component.html',
  styleUrls: ['./show-list.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class ShowListComponent implements OnInit {
  settings = {
    columns: {
      userId: {
        title: 'userId',
        type: 'string',
      },
      username: {
        title: 'username',
        type: 'string',
      },
      email: {
        title: 'email',
        type: 'string',
      },
    },
  };

  source: LocalDataSource = new LocalDataSource();

  constructor(
    private userService: UserService,
    private dataService: DataService,
    private router: Router
  ) {
    this.dataService.getAllShows().subscribe(data => this.source.load(data));
  }

  ngOnInit(): void {
  }


  onDeleteConfirm(event): void {
    if (window.confirm('Are you sure you want to delete?')) {
      event.confirm.resolve();
    } else {
      event.confirm.reject();
    }
  }

  userRowSelect($event: any) {
    // const entry: User = $event.data;
    // this.router.navigate(['/pages/environments/view/' + entry.id]);
  }
}
