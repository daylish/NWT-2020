import { Platform } from './platform';

export interface Stream {
    id: number;
    link: string;
    itemId: number;
    platform: Platform;
}