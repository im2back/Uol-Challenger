import { Component,EventEmitter, Output } from '@angular/core';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { FormsModule, NgModel,NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { UolService } from '../service/UolService';
import { PlayerCompleteDto } from '../interfaces/PlayerCompleteDto';

import { RadioButtonModule } from 'primeng/radiobutton';


@Component({
  selector: 'app-form-component',
  standalone: true,
  imports: [CommonModule,FormsModule,ButtonModule,InputTextModule,RadioButtonModule],
  templateUrl: './form-component.component.html',
  styleUrl: './form-component.component.css'
})
export class FormComponentComponent {
  constructor(private service : UolService) {}

  @Output() messageEvent = new EventEmitter<string|null>();
  @Output() playersListUpdated = new EventEmitter<PlayerCompleteDto[]>();

  selectedGroup: string = '';

  groups: any[] = [
      { name: 'Vingadores', value: 'VINGADORES' },
      { name: 'Liga da Justiça', value: 'LIGA_DA_JUSTICA' },
  ];

  listaSelecionada: string = '';

  listas: any[] = [
      { name: 'Lista vingadores', value: 'vingadores' },
      { name: 'Lista liga da justiça', value: 'justice' },
      { name: 'Ambas', value: 'all' },
  ];

  message: string | null = null;

  cadastrarPlayer(form : NgForm){
    this.service.cadastrarPlayer(form.value,this.listaSelecionada).subscribe(
      (response) => {
        // Tratamento em caso de sucesso
        this.message = (`${response.name} cadastrado com sucesso`);
        this.messageEvent.emit(this.message);
        console.log(response);
        form.reset();
      },
      (error) => {
        // Tratamento em caso de erro
        console.log(error);
        this.message = 'Erro ao cadastrar: ' + (error.error.message || 'Erro desconhecido');
        this.messageEvent.emit(this.message);
      }
    );
  }

  playersList: PlayerCompleteDto[]= [];
  listarTodos(){
    this.service.listarTodos().subscribe((response) =>{
      this.playersList=response
      this.playersListUpdated.emit(this.playersList)
    }
    )}

}
