import { Component, Input,SimpleChanges } from '@angular/core';

import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-message',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './message.component.html',
  styleUrl: './message.component.css'
})
export class MessageComponent {

  @Input() message: string | null = null;
  errors: string[] = [];


  ngOnChanges(changes: SimpleChanges): void {
    // Acessando a propriedade 'message' do objeto 'changes' corretamente
    if (changes['message'] && changes['message'].currentValue) {
      // Aqui você precisa acessar `currentValue` da propriedade `message`
      this.processMessage(changes['message'].currentValue);
    }
  }

  private processMessage(message: string): void {
    const errorsArray = message.split(",");
    this.errors = errorsArray.map(error => error.trim());  // Trim any extra whitespace from each error message
  }

}
