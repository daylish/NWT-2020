import {UserService} from './user.service';
import {HttpHeaders, HttpParams} from '@angular/common/http';

interface HttpOptions {
  headers?: HttpHeaders | {
    [header: string]: string | string[];
  };
}

export class ServiceUtils {
  static GetHttpOptions(userService: UserService): HttpOptions {
    return {
      headers: {
        Authorization: userService.getLoggedUser().token
      }
    };
  }
}
