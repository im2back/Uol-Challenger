import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PlayerCadastroDto } from '../homepage-module/interfaces/PlayerCadastroDto';
import { PlayerCompleteDto } from '../homepage-module/interfaces/PlayerCompleteDto';

@Injectable({
  providedIn: 'root'
})

export class UpdateService {

  private readonly API =  "http://localhost:8080/"
  constructor( private http : HttpClient) {}

  listarPlayer (id:string) : Observable <PlayerCompleteDto> {
    return this.http.get<PlayerCompleteDto> (`${this.API}player/${id}`);
  }

  atualizarPlayer (player: PlayerCompleteDto) : Observable<PlayerCompleteDto>  {
    return this.http.put<PlayerCompleteDto> (`${this.API}player/update`, player);
  }

}
