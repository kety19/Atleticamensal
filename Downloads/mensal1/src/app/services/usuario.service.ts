import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Usuario } from '../models/usuario.model';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private usuarios: Usuario[] = [
    { id: 1, nome: 'Usuário 1', email: 'usuario1@example.com', senha: 'senha123', atleticaId: 1, role: 'VISUALIZADOR' },
    { id: 2, nome: 'Usuário 2', email: 'usuario2@example.com', senha: 'senha123', atleticaId: 2, role: 'ATLETICA' }
  ];

  constructor() {}

  // Retorna todos os usuários
  getUsuarios(): Observable<Usuario[]> {
    return of(this.usuarios);
  }

  // Adiciona um novo usuário e define como o usuário atual
  addUsuario(usuario: Usuario): Observable<Usuario> {
    if (usuario.nome && usuario.email && usuario.senha && usuario.atleticaId && usuario.role) {
      usuario.id = this.usuarios.length + 1;
      this.usuarios.push(usuario);
      this.setCurrentUser(usuario); // Define o usuário recém-adicionado como o usuário logado
    }
    return of(usuario);
  }

  // Deleta um usuário com base no ID
  deleteUsuario(id: number): Observable<void> {
    this.usuarios = this.usuarios.filter(usuario => usuario.id !== id);
    return of(undefined);
  }

  // Define o usuário logado no localStorage
  setCurrentUser(usuario: Usuario): void {
    console.log('Salvando usuário no localStorage:', usuario);
    localStorage.setItem('currentUser', JSON.stringify(usuario));
  }

  // Retorna o papel do usuário logado
  getCurrentUserRole(): 'VISUALIZADOR' | 'ATLETICA' | null {
    const user = localStorage.getItem('currentUser');
    const role = user ? JSON.parse(user).role : null;
    console.log('Papel do usuário logado:', role);
    return role;
  }

  // Verifica se o usuário logado é do tipo ATLETICA
  isAtleticaUser(): boolean {
    return this.getCurrentUserRole() === 'ATLETICA';
  }

  // Método opcional para sair
  logout(): void {
    localStorage.removeItem('currentUser');
  }
}
