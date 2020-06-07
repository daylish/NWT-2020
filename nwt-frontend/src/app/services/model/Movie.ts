import {Review} from './Review';

export interface Movie {
  movieID: number;
  title: string;
  description: string;
  genre: string;
  year: number;
  reviews: Review[];
  creatorID: number;
}
