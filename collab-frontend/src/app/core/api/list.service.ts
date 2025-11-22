import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BoardList } from '../models/list.model';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ListService {

  private readonly http = inject(HttpClient);

  updateList(boardId: string, listId: string, payload: Partial<BoardList>): Observable<BoardList> {
    return this.http.put<BoardList>(`boards/${boardId}/lists/${listId}`, payload);
  }

  deleteList(boardId: string, listId: string): Observable<void> {
    return this.http.delete<void>(`boards/${boardId}/lists/${listId}`);
  }
}
