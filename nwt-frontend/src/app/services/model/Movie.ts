import {Review} from './Review';

export interface Movie {
  movieId: number;
  title: string;
  description: string;
  genre: string;
  year: number;
  reviews: Review[];
  creatorId: number;
}
