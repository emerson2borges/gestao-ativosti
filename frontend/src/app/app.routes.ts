import { Routes } from '@angular/router';
import { AppLayoutComponent } from './layout/app-layout/app-layout.component';
import { CidadesListComponent } from './pages/cidades/cidades-list.component';
import { LocalizacoesListComponent } from './pages/localizacoes/localizacoes-list.component';
import { OrdensCompraListComponent } from './pages/ordens-compra/ordens-compra-list.component';
import { TiposAtivoListComponent } from './pages/tipos-ativo/tipos-ativo-list.component';
import { AtivosListComponent } from './pages/ativos/ativos-list.component';
import { SubativosListComponent } from './pages/subativos/subativos-list.component';
import { EstoqueListComponent } from './pages/estoque/estoque-list.component';

export const routes: Routes = [
  {
    path: '',
    component: AppLayoutComponent,
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'cidades', component: CidadesListComponent },
      { path: 'localizacoes', component: LocalizacoesListComponent },
      { path: 'ordens-compra', component: OrdensCompraListComponent },
      { path: 'tipos-ativo', component: TiposAtivoListComponent },
      { path: 'ativos', component: AtivosListComponent },
      { path: 'subativos', component: SubativosListComponent },
      { path: 'estoque', component: EstoqueListComponent },
    ]
  },
  { path: '**', redirectTo: '' }
];
