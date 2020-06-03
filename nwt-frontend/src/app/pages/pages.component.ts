import { Component, OnInit } from '@angular/core';
import {NbMenuItem} from '@nebular/theme';
import {User} from '../user';
import {JwtInfo, UserService} from '../services/user.service';

@Component({
  selector: 'app-pages',
  templateUrl: './pages.component.html',
  styleUrls: ['./pages.component.scss']
})
export class PagesComponent implements OnInit {
  userMenu: NbMenuItem[] = [
    { title: 'Profile' },
    { title: 'Log out', link: '/auth/login' },
  ];

  user: JwtInfo;

  menu: NbMenuItem[] = [];

  roleUserMenu: NbMenuItem[] = [];
  adminMenu: NbMenuItem[] = [
    {
      title: 'User List',
      icon: 'home-outline',
      link: '/pages/user-list',
    },
  ];

  constructor(private userService: UserService) {
    this.user = userService.getLoggedUser().user;

    if (this.user.authorities.indexOf('user') >= 0) {
      this.menu.push(...this.roleUserMenu);
    }
    if (this.user.authorities.indexOf('admin') >= 0) {
      this.menu.push(...this.adminMenu);
    }
  }

  ngOnInit() {
  }

  navigateHome() {

  }
}
