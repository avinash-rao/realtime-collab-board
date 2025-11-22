import { Routes } from '@angular/router';
import { BoardsListComponent } from './features/boards/boards-list/boards-list.component';
import { BoardDetailComponent } from './features/boards/board-detail/board-detail.component';

export const routes: Routes = [
    { path: '', component: BoardsListComponent},
    { path: 'boards/:id', component: BoardDetailComponent }
];
