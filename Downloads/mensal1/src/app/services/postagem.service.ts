// postagem.service.ts
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Postagem } from '../models/postagem.model';

@Injectable({
  providedIn: 'root',
})
export class PostagemService {
  private postagens: Postagem[] = [
    {
      id: 1,
      titulo: 'Evento de Atletismo',
      descricao: 'Evento esportivo de corrida e salto',
      data: new Date(),
      local: 'Estádio Municipal',
      imagemUrl: 'https://example.com/image1.jpg',
    },
    {
      id: 2,
      titulo: 'Festa das Atléticas',
      descricao: 'Festa de integração das atléticas',
      data: new Date(),
      local: 'Clube Recreativo',
      imagemUrl: 'https://example.com/image2.jpg',
    },
  ];

  constructor() {}

  getPostagens(): Observable<Postagem[]> {
    return of(this.postagens);
  }

  addPostagem(postagem: Postagem): Observable<Postagem> {
    this.postagens.push({ ...postagem, id: this.postagens.length + 1 });
    return of(postagem);
  }

  deletePostagem(id: number): Observable<void> {
    this.postagens = this.postagens.filter(postagem => postagem.id !== id);
    return of(undefined);
  }
}
