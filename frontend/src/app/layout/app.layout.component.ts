import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { FooterComponent } from './footer/footer.component';

@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    HeaderComponent,
    SidebarComponent,
    FooterComponent
  ],
  template: `
    <div class="layout-wrapper">
      <app-sidebar></app-sidebar>
      
      <div class="layout-main">
        <app-header></app-header>
        
        <main class="content-body">
          <router-outlet></router-outlet>
        </main>

        <app-footer></app-footer>
      </div>
    </div>
  `,
  styles: [`
    .layout-wrapper {
      display: flex;
      min-height: 100vh;
      width: 100%;
    }

    .layout-main {
      flex: 1;
      margin-left: 260px;
      display: flex;
      flex-direction: column;
      min-height: 100vh;
      background: var(--bg-gradient);
    }

    .content-body {
      flex: 1;
      padding: 2rem;
      max-width: 1600px;
      width: 100%;
      margin: 0 auto;
    }
  `]
})
export class AppLayoutComponent {}
