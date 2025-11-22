import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { BoardStore } from 'src/app/core/stores/board.store';
import { CardFilterPipe } from 'src/app/shared/pipes/card-filter.pipe';

@Component({
  selector: 'app-board-detail',
  standalone: true,
  imports: [CommonModule, CardFilterPipe],
  templateUrl: './board-detail.component.html',
  styleUrls: ['./board-detail.component.scss']
})
export class BoardDetailComponent implements OnInit {

  private route = inject(ActivatedRoute);
  boardStore = inject(BoardStore);

  boardId = '';

  lists = this.boardStore.lists$;
  cards = this.boardStore.cards$;
  board = this.boardStore.board$;

  ngOnInit() {
    this.boardId = this.route.snapshot.paramMap.get('id')!;
    this.boardStore.loadBoard(this.boardId);
  }

}
