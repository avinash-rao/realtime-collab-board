import { inject, Injectable } from "@angular/core";
import { CardService } from "../api/card.service";
import { firstValueFrom } from "rxjs";
import { BoardStore } from "./board.store";
import { Card } from "../models/card.model";

@Injectable({ providedIn: 'root' })
export class CardStore {

  private cardService = inject(CardService);
  private boardStore = inject(BoardStore);

  /** Create a new card */
  async createCard(payload: {
    boardId: string;
    listId: string;
    title: string;
    description?: string;
    position: number;
  }) {
    const newCard = await firstValueFrom(
      this.cardService.createCard(payload)
    );
    this.boardStore.addCardLocal(newCard);
    return newCard;
  }

  async updateCard(cardId: string, patch: Partial<Card>) {
    const updated = await firstValueFrom(
      this.cardService.updateCard(cardId, patch)
    );
    this.boardStore.updateCardLocal(cardId, updated);
    return updated;
  }

  async moveCard(cardId: string, targetListId: string, newPosition: number) {
    // Local optimistic update
    this.boardStore.moveCardLocal(cardId, targetListId, newPosition);

    // Sync with backend
    const updated = await firstValueFrom(
        this.cardService.moveCard(cardId, targetListId, newPosition)
    );

    this.boardStore.updateCardLocal(cardId, updated);
    return updated;
  }

  /** Delete a card */
  async deleteCard(cardId: string) {
    await firstValueFrom(
      this.cardService.deleteCard(cardId)
    );
    // remove locally
    this.boardStore.deleteCardLocal(cardId);
  }

}