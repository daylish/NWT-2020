import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Config} from './config';
import {Movie} from './model/Movie';
import {Observable} from 'rxjs';
import {List} from '../list';
import {UserService} from './user.service';
import {ServiceUtils} from './ServiceUtils';
import {Review} from './model/Review';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private url = Config.basepath + '/data-microservice';

  constructor(private http: HttpClient, private userService: UserService) {
  }

  getAllMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(this.url + '/movies', ServiceUtils.GetHttpOptions(this.userService));
  }

  getMovieById(id: number): Observable<Movie> {
    return this.http.get<Movie>(this.url + '/movies/' + id, ServiceUtils.GetHttpOptions(this.userService));
  }

  createMovie(movie: Movie): Observable<Movie> {
    return this.http.post<Movie>(this.url + '/movies/new', movie, ServiceUtils.GetHttpOptions(this.userService));
  }

  updateMovie(movie: Movie): Observable<Movie> {
    return this.http.put<Movie>(this.url + '/movies/edit/' + movie.movieID, movie, ServiceUtils.GetHttpOptions(this.userService));
  }

  deleteMovie(id: number): Observable<any> {
    return this.http.delete(this.url + '/movies/delete/' + id, ServiceUtils.GetHttpOptions(this.userService));
  }

  getMoviesByUser(id: number): Observable<Movie[]> {
    return this.http.get<Movie[]>(this.url + '/movies/creator/' + id, ServiceUtils.GetHttpOptions(this.userService));
  }

  getAllReviews(): Observable<Review[]> {
    return this.http.get<Review[]>(this.url + '/reviews', ServiceUtils.GetHttpOptions(this.userService));
  }
}
