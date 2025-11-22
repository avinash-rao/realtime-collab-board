import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BoardService } from 'src/app/core/api/board.service';
import { Router } from '@angular/router';
import { toSignal } from '@angular/core/rxjs-interop';
import { Board } from 'src/app/core/models/board.model';
import { NgFor, NgIf } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-boards-list',
  standalone: true,
  imports: [CommonModule, NgFor, NgIf, MatCardModule, MatButtonModule],
  templateUrl: './boards-list.component.html',
  styleUrls: ['./boards-list.component.scss']
})
export class BoardsListComponent {

  private boardService = inject(BoardService);
  private router = inject(Router);

  // Using toSignal -> modern Angular API
  boards = toSignal(this.boardService.getBoards(), {initialValue: [] as Board[]});

  openBoard(board: Board) {
    this.router.navigate(['/boards', board.id]);
  }
}
