import { UpdateService } from './../UpdateService';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RadioButtonModule } from 'primeng/radiobutton';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { PlayerCompleteDto } from '../../homepage-module/interfaces/PlayerCompleteDto';
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';


@Component({
  selector: 'app-edicao-component',
  standalone: true,
  imports: [RadioButtonModule,CommonModule,FormsModule,InputTextModule,ButtonModule,HttpClientModule],
  templateUrl: './edicao-component.component.html',
  providers : [UpdateService],
  styleUrl: './edicao-component.component.css'
})
export class EdicaoComponentComponent {
  constructor(private route: ActivatedRoute, private service :UpdateService,private router: Router) { }

  groups: any[] = [
    { name: 'Vingadores', value: 'VINGADORES' },
    { name: 'Liga da Justiça', value: 'LIGA_DA_JUSTICA' },
];

  IdRetornado : String = '';
  nameRetornado : string = '';
  emailRetornado :string = '';
  phoneRetornado :string = '';
  aliasRetornado :string = '';
  selectedGroup: string = '';


  playerIdParam: string = '';
  player: PlayerCompleteDto | null = null;

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id !== null) {
      this.playerIdParam = id;
      this.service.listarPlayer(this.playerIdParam).subscribe((response) => {
        this.player = response;
        this.IdRetornado = String(response.id);
        this.nameRetornado = String(response.name)
        this.emailRetornado = String(response.email);
        this.phoneRetornado = String(response.phone);
        this.aliasRetornado = String(response.alias);
        this.selectedGroup = String(response.group);

      }, error => {
        console.error('Erro ao carregar o jogador:', error);
      });
    } else {
      console.error('ID do jogador é nulo');

    }
  }

  atualizarPlayer(form : NgForm){
    this.service.atualizarPlayer(form.value).subscribe((response)=>
      {
        this.router.navigate(['/cadastro']);
      }

    ) }

}
