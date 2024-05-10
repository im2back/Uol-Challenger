import { Routes } from '@angular/router';
import { HomepageComponentComponent } from './homepage-module/main-component/main-component.component';
import { EdicaoComponentComponent } from './update-module/edicao-component/edicao-component.component';

export const routes: Routes = [


  {
    path : 'cadastro',
  component : HomepageComponentComponent
  },
  {
    path : 'atualizar/:id',
  component : EdicaoComponentComponent
  },

  {
    path : '',
    redirectTo : 'cadastro',
    pathMatch: 'full'
  }
];
