import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../../../services/user.service';

const REMEMBER_ME_ITEM = 'login_remembered_user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    user = 'nluebbert0';
    pass = 'passwordFillerBecausePasswordsAreComplicated';

    rememberMe = false;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
      const saved = localStorage.getItem(REMEMBER_ME_ITEM);
      if ( saved !== null ) {
          this.user = saved;
      }
  }

    doLogin() {
        if ( this.rememberMe ) {
          localStorage.setItem(REMEMBER_ME_ITEM, this.user);
        }

        this.userService.login(this.user, this.pass).subscribe(o => {
            this.router.navigate(['/']);
        });
    }
}
