import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
// 1. Importe o módulo do botão do PrimeNG
import { ButtonModule } from 'primeng/button'; 

@Component({
  selector: 'app-root',
  standalone: true,
  // 2. Adicione o ButtonModule na lista de imports
  imports: [RouterOutlet, ButtonModule], 
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'frontend-app';
}