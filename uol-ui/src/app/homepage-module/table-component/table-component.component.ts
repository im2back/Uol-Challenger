import { Component,Input, Output,EventEmitter,OnChanges, SimpleChanges } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { PlayerCompleteDto } from '../interfaces/PlayerCompleteDto';
import { RouterModule, Routes } from '@angular/router';
import { UolService } from '../service/UolService';

@Component({
  selector: 'app-table-component',
  standalone: true,
  imports: [FormsModule,CommonModule,RouterModule],
  templateUrl: './table-component.component.html',
  styleUrl: './table-component.component.css'
})
export class TableComponentComponent {
constructor(private service : UolService) {}

@Input() playersList: PlayerCompleteDto[] = [];

@Output() playersDelete = new EventEmitter<PlayerCompleteDto[]>();

playersListAtualizada: PlayerCompleteDto[]= [];

listarTodos(){
  this.service.listarTodos().subscribe((response) =>{
    this.playersListAtualizada=response
    this.playersDelete.emit(this.playersListAtualizada)
  }
  )}

  excluirPlayer(playerId: number): void {
    this.service.deletePlayer(playerId).subscribe({
      next: (response) => {
        this.listarTodos();
      },
      error: (error) => {
        console.error('Erro ao excluir o jogador:', error);
      }
    });
  }

}
