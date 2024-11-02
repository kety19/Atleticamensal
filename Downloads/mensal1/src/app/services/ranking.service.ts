import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Ranking } from '../models/ranking.model';

@Injectable({
  providedIn: 'root'
})
export class RankingService {
  private rankings: Ranking[] = [
    { id: 1, usuarioId: 1, atleticaId: 1, pontos: 100, posicao: 1 },
    { id: 2, usuarioId: 2, atleticaId: 1, pontos: 80, posicao: 2 },
    { id: 3, usuarioId: 3, atleticaId: 2, pontos: 90, posicao: 1 },
    { id: 4, usuarioId: 4, atleticaId: 2, pontos: 70, posicao: 2 }
  ];

  constructor() {}

  getRankings(): Observable<Ranking[]> {
    return of(this.rankings); // Retorna todos os rankings
  }

  getRankingsByAtletica(atleticaId: number): Observable<Ranking[]> {
    const filteredRankings = this.rankings.filter(ranking => ranking.atleticaId === atleticaId);
    return of(filteredRankings); // Retorna os rankings filtrados
  }

  addRanking(ranking: Ranking): Observable<Ranking> {
    // Garante que o usuarioId, atleticaId e posicao sejam válidos
    if (ranking.usuarioId && ranking.atleticaId && ranking.posicao !== undefined) {
      this.rankings.push({ ...ranking, id: this.rankings.length + 1 }); // Simula a adição
    }
    return of(ranking); // Retorna o ranking adicionado
  }

  deleteRanking(id: number): Observable<void> {
    this.rankings = this.rankings.filter(ranking => ranking.id !== id); // Simula a exclusão
    return of(undefined); // Retorna nada
  }
}
