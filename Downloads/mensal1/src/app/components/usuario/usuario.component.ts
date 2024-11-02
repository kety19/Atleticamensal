// usuario.component.ts
import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../../services/usuario.service';
import { Usuario } from '../../models/usuario.model';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-usuario',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.scss']
})
export class UsuarioComponent implements OnInit {
  usuarios: Usuario[] = [];
  newUsuario: Usuario = { id: 0, nome: '', email: '', senha: '', atleticaId: 0, role: 'VISUALIZADOR' };

  constructor(private usuarioService: UsuarioService, private router: Router) {}

  ngOnInit(): void {
    this.loadUsuarios();
  }

  loadUsuarios(): void {
    this.usuarioService.getUsuarios().subscribe(data => this.usuarios = data);
  }

  addUsuario(): void {
    this.usuarioService.addUsuario(this.newUsuario).subscribe(() => {
      this.loadUsuarios();
      this.newUsuario = { id: 0, nome: '', email: '', senha: '', atleticaId: 0, role: 'VISUALIZADOR' };
      this.router.navigate(['/postagem']); // Redireciona para o componente de postagem
    });
  }

  goToAtletica(): void {
    this.router.navigate(['/atletica']); // Redireciona para o componente de cadastro de atlética
  }

  deleteUsuario(id: number): void {
    this.usuarioService.deleteUsuario(id).subscribe(() => this.loadUsuarios());
  }
}
