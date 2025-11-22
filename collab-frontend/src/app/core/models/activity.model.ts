export interface Activity {
  id: string;
  boardId: string;
  actorId: string;
  verb: string;
  subjectId: string;
  message: string;
  details?: any;
  createdAt: string;
}
