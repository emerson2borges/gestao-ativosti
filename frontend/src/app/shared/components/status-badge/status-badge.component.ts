import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-status-badge',
  standalone: true,
  imports: [CommonModule],
  template: `
    <span class="status-badge" [ngClass]="getBadgeClass()">
      <i [class]="getIconClass()"></i>
      {{ status }}
    </span>
  `,
  styles: [`
    .status-badge {
      display: inline-flex;
      align-items: center;
      gap: 0.35rem;
      padding: 0.3rem 0.75rem;
      border-radius: 9999px;
      font-size: 0.75rem;
      font-weight: 600;
      letter-spacing: 0.02em;
    }

    .badge-em-uso {
      background: #dcfce7;
      color: #15803d;
    }

    .badge-disponivel {
      background: #e0e7ff;
      color: #4338ca;
    }

    .badge-manutencao {
      background: #fef3c7;
      color: #b45309;
    }

    .badge-descartado {
      background: #fee2e2;
      color: #b91c1c;
    }

    .badge-default {
      background: #f1f5f9;
      color: #475569;
    }
  `]
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
