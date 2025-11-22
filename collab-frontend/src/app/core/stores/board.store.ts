import { computed, inject, Injectable, signal } from "@angular/core";
import { Board } from "../models/board.model";
import { Card } from "../models/card.model";
import { CardService } from "../api/card.service";
import { BoardService } from "../api/board.service";
import { firstValueFrom } from "rxjs";
import { BoardList } from "../models/list.model";

@Injectable({ providedIn: 'root' })
export class BoardStore {
  
    private board = signal<Board | null>(null);
    private cards = signal<Card[]>([]);
    private loading = signal(false);

    // Expose derived state
    board$ = computed(() => this.board());
    lists$ = computed(() => this.board()?.lists || []);
    cards$ = computed(() => this.cards());
    
    private boardService = inject(BoardService);
    private cardService = inject(CardService);

    /** Load everything for a board */
    async loadBoard(boardId: string) {
        this.loading.set(true);
        
        // Load board metadata
        const board = await firstValueFrom(
            this.boardService.getBoard(boardId)
        );
        this.board.set(board);
        
        // Load its cards
        const cards = await firstValueFrom(
            this.cardService.getCardsForBoard(boardId)
        );
        this.cards.set(cards);

        this.loading.set(false);
    }

    /** Add a new list locally */
    addListLocal(list: BoardList) {
        this.board.update(board => {
            if (!board) return board;
            return {
                ...board,
                lists: [...board.lists, list]
            };
        });
    }

    addCardLocal(card: Card) {
        this.cards.update(cards => [...cards, card]);
    }

    updateCardLocal(cardId: string, updatedFields: Partial<Card>) {
        this.cards.update(cards => cards.map(card => 
            card.id === cardId ? { ...card, ...updatedFields } : card
        ));
    }

    moveCardLocal(cardId: string, targetListId: string, newPosition: number) {
        this.cards.update(cards => cards.map(card => 
            card.id === cardId 
                ? { ...card, listId: targetListId, position: newPosition } 
                : card
        ));
    }

    deleteCardLocal(cardId: string) {
        this.cards.update(cards => cards.filter(card => card.id !== cardId));
    }

    updateListLocal(listId: string, updatedList: BoardList) {
        this.board.update(board => {
            if (!board) return board;
            return {
                ...board,
                lists: board.lists.map(l => l.id === listId ? updatedList : l)
            };
        });
    }

    deleteListLocal(listId: string) {
        this.board.update(board => {
            if (!board) return board;
            return {
                ...board,
                lists: board.lists.filter(l => l.id !== listId)
            };
        });
    }
}
