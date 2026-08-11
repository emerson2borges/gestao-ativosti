import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

export interface MenuItem {
  label: string;
  icon: string;
  routerLink: string;
  badge?: string;
}

export interface MenuSection {
  title: string;
  items: MenuItem[];
}

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss'
})
export class SidebarComponent {
  menuSections: MenuSection[] = [
    {
      title: 'Principal',
      items: [
        { label: 'Dashboard', icon: 'pi pi-chart-bar', routerLink: '/dashboard' }
      ]
    },
    {
      title: 'Gestão de Ativos',
      items: [
        { label: 'Ativos TI', icon: 'pi pi-desktop', routerLink: '/ativos' },
        { label: 'Tipos de Ativo', icon: 'pi pi-tags', routerLink: '/tipos-ativo' },
        { label: 'Subativos Internos', icon: 'pi pi-server', routerLink: '/subativos' }
      ]
    },
    {
      title: 'Suprimentos & Compras',
      items: [
        { label: 'Estoque de Insumos', icon: 'pi pi-box', routerLink: '/estoque' },
        { label: 'Ordens de Compra', icon: 'pi pi-shopping-cart', routerLink: '/ordens-compra' }
      ]
    },
    {
      title: 'Cadastros & Registros',
      items: [
        { label: 'Localizações', icon: 'pi pi-building', routerLink: '/localizacoes' },
        { label: 'Cidades / Filiais', icon: 'pi pi-map-marker', routerLink: '/cidades' },
        { label: 'Histórico & Logs', icon: 'pi pi-history', routerLink: '/historico' }
      ]
    }
  ];
}
