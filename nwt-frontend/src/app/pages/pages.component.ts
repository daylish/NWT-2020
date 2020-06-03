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


  constructor(private userService: UserService) {
    this.user = userService.getLoggedUser().user;
  }

  ngOnInit() {
  }

  navigateHome() {

  }
}
