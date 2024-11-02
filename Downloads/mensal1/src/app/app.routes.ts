// app.routes.ts
import { Routes } from '@angular/router';
import { InicioComponent } from './components/inicio/inicio.component';
import { AtleticaComponent } from './components/atletica/atletica.component';
import { PostagemComponent } from './components/postagem/postagem.component';
import { RankingComponent } from './components/ranking/ranking.component';
import { UsuarioComponent } from './components/usuario/usuario.component';

export const routes: Routes = [
  { path: '', component: InicioComponent }, // Página inicial
  { path: 'atletica', component: AtleticaComponent },
  { path: 'postagem', component: PostagemComponent },
  { path: 'ranking', component: RankingComponent },
  { path: 'usuario', component: UsuarioComponent },
  { path: '**', redirectTo: '', pathMatch: 'full' }, // Redireciona para a página inicial em caso de rota inválida
];
