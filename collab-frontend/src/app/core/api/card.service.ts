import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Card } from '../models/card.model';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class CardService {

  private readonly http = inject(HttpClient);

  getCardsForBoard(boardId: string): Observable<Card[]> {
    return this.http.get<Card[]>(`boards/${boardId}/cards`);
  }

  createCard(payload: {
    boardId: string;
    listId: string;
    title: string;
    description?: string;
    position: number;
  }): Observable<Card> {
    return this.http.post<Card>('cards', payload);
  }

  updateCard(cardId: string, payload: Partial<Card>): Observable<Card> {
    return this.http.put<Card>(`cards/${cardId}`, payload);
  }

  moveCard(cardId: string, targetListId: string, newPosition: number): Observable<Card> {
    return this.http.put<Card>(`cards/${cardId}/move`, null, {
      params: {
        targetListId,
        newPosition
      }
    });
  }

  deleteCard(cardId: string): Observable<void> {
    return this.http.delete<void>(`cards/${cardId}`);
  }
}
