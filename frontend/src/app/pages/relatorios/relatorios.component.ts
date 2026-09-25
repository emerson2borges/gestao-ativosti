import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { PageHeaderComponent } from '../../shared/components/page-header/page-header.component';

@Component({
  selector: 'app-relatorios',
  standalone: true,
  imports: [
    CommonModule,
    ButtonModule,
    PageHeaderComponent
  ],
  templateUrl: './relatorios.component.html',
  styleUrl: './relatorios.component.scss'
})
export class RelatoriosComponent {

  exportar(tipo: string, formato: 'csv' | 'pdf'): void {
    alert(`Gerando relatório de ${tipo.toUpperCase()} no formato ${formato.toUpperCase()}...`);
  }
}
