import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {LocalDataSource} from 'ng2-smart-table';
import {UserService} from '../../services/user.service';
import {SmartTableData} from '../../@core/data/smart-table';
import {Router} from '@angular/router';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class UserListComponent implements OnInit {
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
      password: {
        title: 'password',
        type: 'string',
      },
      email: {
        title: 'email',
        type: 'string',
      },
    },
  };

  source: LocalDataSource = new LocalDataSource();

  constructor(private userService: UserService, private router: Router) {
    this.userService.getUsers().subscribe(users => this.source.load(users));
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
