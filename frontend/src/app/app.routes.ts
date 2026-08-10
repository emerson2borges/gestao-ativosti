import { Routes } from '@angular/router';
import { AppLayoutComponent } from './layout/app-layout/app-layout.component';
import { CidadesListComponent } from './pages/cidades/cidades-list.component';
import { LocalizacoesListComponent } from './pages/localizacoes/localizacoes-list.component';

export const routes: Routes = [
  {
    path: '',
    component: AppLayoutComponent,
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'cidades', component: CidadesListComponent },
      { path: 'localizacoes', component: LocalizacoesListComponent },
    ]
  },
  { path: '**', redirectTo: '' }
];
