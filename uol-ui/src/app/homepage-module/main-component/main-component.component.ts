import {  Component,Output, EventEmitter } from '@angular/core';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { FormsModule, NgModel,NgForm } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { CommonModule } from '@angular/common';
import { UolService } from '../service/UolService';
import { PlayerCompleteDto } from '../interfaces/PlayerCompleteDto';
import { HttpClientModule } from '@angular/common/http';
import { MessageComponent } from '../message-component/message/message.component';

import { TableComponentComponent } from '../table-component/table-component.component';
import { FormComponentComponent } from '../form-component/form-component.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-homepage-component',
  standalone: true,
  imports: [InputTextModule,ButtonModule,FormsModule,TableModule,CommonModule,
    HttpClientModule,MessageComponent,TableComponentComponent,FormComponentComponent],
  providers : [UolService],
  templateUrl: './main-component.component.html',
  styleUrl: './main-component.component.css'
})
export class HomepageComponentComponent {

  constructor(private service : UolService,private router: Router) {}


  message: string | null = null;


  playersList: PlayerCompleteDto[] = [];

  onMessageReceived(newMessage: string | null) {
    this.message = newMessage;
    setTimeout(() => {
      this.message = null;
    }, 5000);
  }

  onPlayersListUpdated(newPlayersList: PlayerCompleteDto[]) {
    this.playersList = newPlayersList;
  }

  atualizarPage(playerList: PlayerCompleteDto[]){
     this.playersList  = playerList;
  }


}


