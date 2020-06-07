import {ShowEpisode} from './ShowEpisode';

export interface Show {
  showID: number;
  title: string;
  description: string;
  genre: string;
  year: number;
  episodes: ShowEpisode[];
  creatorID: number;
}
