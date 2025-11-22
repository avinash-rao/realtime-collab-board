import { Comment } from './comment.model';
import { ChecklistItem } from './checklist.model';
import { Label } from './label.model';

export interface Card {
  id: string;
  boardId: string;
  listId: string;

  title: string;
  description?: string;

  labels?: Label[];
  assignees?: string[]; // userIds
  dueDate?: string;

  checklist?: ChecklistItem[];
  comments?: Comment[];

  position: number;
  createdAt?: string;
}
