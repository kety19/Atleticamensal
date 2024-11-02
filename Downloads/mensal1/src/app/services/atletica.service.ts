import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Atletica } from '../models/atletica.model';

@Injectable({
  providedIn: 'root'
})
export class AtleticaService {
  private atleticas: Atletica[] = [
    { id: 1, nome: 'Ursao', universidade: 'Uniamerica', descricao: 'Descrição da Atlética' },
    { id: 2, nome: 'Quati', universidade: 'UDC', descricao: 'Descrição da Atlética B' }
  ];

  constructor() {}

  getAtleticas(): Observable<Atletica[]> {
    return of(this.atleticas); // Retorna os dados mockados
  }

  createAtletica(atletica: Atletica): Observable<Atletica> {
    this.atleticas.push({ ...atletica, id: this.atleticas.length + 1 }); // Simula a adição
    return of(atletica); // Retorna a atlética adicionada
  }

  deleteAtletica(id: number): Observable<void> {
    this.atleticas = this.atleticas.filter(atletica => atletica.id !== id); // Simula a exclusão
    return of(undefined); // Retorna nada
  }
}
