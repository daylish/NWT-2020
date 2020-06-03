import {Injectable} from '@angular/core';
import {CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot} from '@angular/router';
import {UserService} from '../../services/user.service';

import {filter, flatMap, map} from 'rxjs/operators';
import {User} from '../../user';
import {Observable} from 'rxjs';

@Injectable()
export class AuthGuardService implements CanActivate {
  private umsSystem: UserService;

  constructor(private userService: UserService, private router: Router) {
    this.umsSystem = this.userService;
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const loggedUser = this.userService.getLoggedUser();
    if (loggedUser !== null) {
      return true;
    } else {
      console.log('navigating to login');
      this.router.navigate(['/auth/login'], {
        queryParams: {
          return: state.url,
        },
      });
      return false;
    }
  }


}
