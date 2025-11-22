import { BoardList } from './list.model';

export interface Board {
  id: string;
  title: string;
  description?: string;
  members: string[]; // userIds
  lists: BoardList[];
}
