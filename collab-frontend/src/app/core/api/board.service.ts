import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Board } from '../models/board.model';
import { Observable } from 'rxjs';
import { BoardList } from '../models/list.model';

@Injectable({ providedIn: 'root' })
export class BoardService {

  private readonly http = inject(HttpClient);

  getBoards(): Observable<Board[]> {
    return this.http.get<Board[]>('boards');
  }

  getBoard(id: string): Observable<Board> {
    return this.http.get<Board>(`boards/${id}`);
  }

  createBoard(payload: { title: string; description?: string; userId: string }): Observable<Board> {
    return this.http.post<Board>(`boards?userId=${payload.userId}`, {
      title: payload.title,
      description: payload.description
    });
  }

  deleteBoard(id: string): Observable<void> {
    return this.http.delete<void>(`boards/${id}`);
  }

  addList(boardId: string, list: { title: string; position: number }): Observable<BoardList> {
    return this.http.post<BoardList>(`boards/${boardId}/lists`, list);
  }

}
