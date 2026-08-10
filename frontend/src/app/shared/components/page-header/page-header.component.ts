import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-page-header',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="page-header">
      <div class="header-titles">
        <h1 class="title">{{ title }}</h1>
        <p *ngIf="subtitle" class="subtitle">{{ subtitle }}</p>
      </div>
      <div class="header-actions">
        <ng-content></ng-content>
      </div>
    </div>
  `,
  styles: [`
    .page-header {
      display: flex;
      align-items: center;
      justify-content: space-between;
      margin-bottom: 2rem;

      .title {
        font-size: 1.5rem;
        font-weight: 700;
        color: #0f172a;
        letter-spacing: -0.02em;
      }

      .subtitle {
        font-size: 0.875rem;
        color: #64748b;
        margin-top: 0.25rem;
      }

      .header-actions {
        display: flex;
        align-items: center;
        gap: 0.75rem;
      }
    }
  `]
})
export class PageHeaderComponent {
  @Input() title: string = '';
  @Input() subtitle?: string;
}
