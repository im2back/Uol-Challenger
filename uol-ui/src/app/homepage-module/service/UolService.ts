import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PlayerCadastroDto } from '../interfaces/PlayerCadastroDto';
import { PlayerCompleteDto } from '../interfaces/PlayerCompleteDto';



@Injectable({
  providedIn: 'root'
})

export class UolService {

private readonly API =  "http://localhost:8080/"
constructor( private http : HttpClient) { }

  listarTodos () : Observable <PlayerCompleteDto[]> {
    return this.http.get<PlayerCompleteDto[]> (`${this.API}player/list-all`);
  }

  cadastrarPlayer (player: PlayerCadastroDto,listaSelecionada:string) : Observable<PlayerCompleteDto>  {
    return this.http.post<PlayerCadastroDto> (`${this.API}register?lista=${listaSelecionada}`,player);
  }

 deletePlayer(id: number): Observable<any> {
    return this.http.delete(`${this.API}player/delete?id=${id}`);
  }

}
