import { Pipe, PipeTransform } from '@angular/core';
import { Card } from 'src/app/core/models/card.model';

@Pipe({
  name: 'cardFilter',
  standalone: true
})
export class CardFilterPipe implements PipeTransform {

  transform(cards: Card[], listId: string): Card[] {
    return cards.filter(c => c.listId === listId);
  }

}
