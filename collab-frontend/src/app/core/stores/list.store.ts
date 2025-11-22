import { Injectable, inject } from '@angular/core';
import { ListService } from '../api/list.service';
import { BoardStore } from './board.store';
import { BoardList } from '../models/list.model';
import { firstValueFrom } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ListStore {

  private listService = inject(ListService);
  private boardStore = inject(BoardStore);

  /** Create a new list */
  async createList(boardId: string, payload: { title: string; position: number }) {
    const list = await firstValueFrom(
      this.listService.updateList(boardId, '', payload) // or POST call
    );
    this.boardStore.addListLocal(list);
    return list;
  }

  /** Update list */
  async updateList(boardId: string, listId: string, patch: Partial<BoardList>) {
    const updated = await firstValueFrom(
      this.listService.updateList(boardId, listId, patch)
    );

    // Local update
    this.boardStore.updateListLocal(listId, updated);

    return updated;
  }

  /** Delete list */
  async deleteList(boardId: string, listId: string) {
    await firstValueFrom(
      this.listService.deleteList(boardId, listId)
    );

    this.boardStore.deleteListLocal(listId);
  }
}