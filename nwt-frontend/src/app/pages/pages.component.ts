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

  menu: NbMenuItem[] = [
    {
      title: 'Dashboard',
      icon: 'home-outline',
      link: '/pages/dashboard',
    },
  ];

  roleUserMenu: NbMenuItem[] = [
    {
      title: 'Movie List',
      icon: 'film-outline',
      link: '/pages/movie',
    },
    {
      title: 'TV Show List',
      icon: 'film-outline',
      link: '/pages/show',
    },
  ];
  adminMenu: NbMenuItem[] = [
    {
      title: 'Add New Movie',
      icon: 'film-outline',
      link: '/pages/movie/create',
    },
    {
      title: 'Add New TV Show',
      icon: 'film-outline',
      link: '/pages/show/create',
    },
    {
      title: 'User List',
      icon: 'people-outline',
      link: '/pages/user',
    },
  ];

  constructor(private userService: UserService) {
    this.user = userService.getLoggedUser().user;

    if (this.user.authorities.indexOf('user') >= 0) {
      this.menu.push(...this.roleUserMenu);
    }
    if (this.user.authorities.indexOf('admin') >= 0) {
      this.menu.push({
        title: 'Admin Menu',
        icon: 'settings-2-outline',
        children: this.adminMenu,
      });
    }
  }

  ngOnInit() {
  }

  navigateHome() {

  }
}
