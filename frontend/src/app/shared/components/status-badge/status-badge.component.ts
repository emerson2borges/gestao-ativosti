import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-status-badge',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './status-badge.component.html',
  styleUrl: './status-badge.component.scss'
})
export class StatusBadgeComponent {
  @Input() status: string = '';

  getBadgeClass(): string {
    const s = (this.status || '').toLowerCase();
    if (s.includes('uso') || s.includes('ativo') || s.includes('instalado')) return 'badge-em-uso';
    if (s.includes('disponivel') || s.includes('disponível') || s.includes('livre')) return 'badge-disponivel';
    if (s.includes('manuten') || s.includes('pendente')) return 'badge-manutencao';
    if (s.includes('descart') || s.includes('desativ')) return 'badge-descartado';
    return 'badge-default';
  }

  getIconClass(): string {
    const s = (this.status || '').toLowerCase();
    if (s.includes('uso') || s.includes('ativo') || s.includes('instalado')) return 'pi pi-check-circle';
    if (s.includes('disponivel') || s.includes('disponível') || s.includes('livre')) return 'pi pi-box';
    if (s.includes('manuten') || s.includes('pendente')) return 'pi pi-cog';
    if (s.includes('descart') || s.includes('desativ')) return 'pi pi-times-circle';
    return 'pi pi-info-circle';
  }
}
