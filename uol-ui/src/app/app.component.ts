
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HomepageComponentComponent } from './homepage-module/main-component/main-component.component';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,HomepageComponentComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

}
