import { Component, OnInit } from '@angular/core';
import { PostagemService } from '../../services/postagem.service';
import { Postagem } from '../../models/postagem.model';
import { UsuarioService } from '../../services/usuario.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-postagem',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './postagem.component.html',
  styleUrls: ['./postagem.component.scss'],
})
export class PostagemComponent implements OnInit {
  postagens: Postagem[] = [];
  newPostagem: Postagem = {
    id: 0,
    titulo: '',
    descricao: '',
    data: new Date(),
    local: '',
    imagemUrl: '',
  };
  isAtletica: boolean = false;

  constructor(
    private postagemService: PostagemService,
    private usuarioService: UsuarioService
  ) {}

  ngOnInit(): void {
    this.checkUserRole(); // Chama o método para verificar o papel do usuário
    this.loadPostagens(); // Carrega as postagens ao iniciar o componente
  }

  // Verifica o papel do usuário e define se ele é "ATLETICA"
  checkUserRole(): void {
    const userRole = this.usuarioService.getCurrentUserRole();
    console.log('Role atual do usuário:', userRole); // Log para depuração
    this.isAtletica = userRole === 'ATLETICA';
  }

  // Carrega todas as postagens do serviço
  loadPostagens(): void {
    this.postagemService.getPostagens().subscribe((data) => {
      this.postagens = data;
    });
  }

  // Adiciona uma nova postagem, apenas se o usuário for "ATLETICA" e os campos obrigatórios estiverem preenchidos
  addPostagem(): void {
    if (this.isAtletica && this.newPostagem.titulo && this.newPostagem.descricao && this.newPostagem.local) {
      this.postagemService.addPostagem(this.newPostagem).subscribe(() => {
        this.loadPostagens();
        this.resetNewPostagem(); // Limpa o formulário de nova postagem
      });
    } else if (!this.isAtletica) {
      alert('Você não tem permissão para criar postagens.');
    } else {
      alert('Por favor, preencha todos os campos obrigatórios.');
    }
  }

  // Exclui uma postagem, apenas se o usuário for "ATLETICA"
  deletePostagem(id: number): void {
    if (this.isAtletica) {
      this.postagemService.deletePostagem(id).subscribe(() => {
        this.loadPostagens();
      });
    } else {
      alert('Você não tem permissão para excluir postagens.');
    }
  }

  // Reseta o formulário de nova postagem
  private resetNewPostagem(): void {
    this.newPostagem = {
      id: 0,
      titulo: '',
      descricao: '',
      data: new Date(),
      local: '',
      imagemUrl: '',
    };
  }
}
