import { Component, OnInit } from '@angular/core';
import { RankingService } from '../../services/ranking.service';
import { AtleticaService } from '../../services/atletica.service'; // Importe o AtleticaService
import { Ranking } from '../../models/ranking.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-ranking',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './ranking.component.html',
  styleUrls: ['./ranking.component.scss']
})
export class RankingComponent implements OnInit {
  rankings: Ranking[] = [];
  groupedRankings: { atleticaId: number; nome: string; pontosTotais: number; usuarioCount: number }[] = []; // Declarando groupedRankings
  atleticas: { id: number; nome: string }[] = []; // Array para armazenar as atléticas

  constructor(
    private rankingService: RankingService,
    private atleticaService: AtleticaService
  ) {}

  ngOnInit(): void {
    this.loadAtleticas(); // Carrega as atléticas ao inicializar
    this.loadRankings(); // Carrega os rankings
  }

  loadAtleticas(): void {
    this.atleticaService.getAtleticas().subscribe(data => {
      this.atleticas = data; // Armazena as atléticas
      this.updateGroupedRankings(); // Atualiza os rankings agrupados depois de carregar as atléticas
    });
  }

  loadRankings(): void {
    this.rankingService.getRankings().subscribe(data => {
      this.rankings = data; // Carrega todos os rankings
      this.updateGroupedRankings(); // Atualiza os rankings agrupados depois de carregar os rankings
    });
  }

  updateGroupedRankings(): void {
    // Zera o array para evitar duplicações
    this.groupedRankings = [];

    // Agrupa rankings por atlética
    this.atleticas.forEach(atletica => {
      const atleticaRankings = this.rankings.filter(r => r.atleticaId === atletica.id);
      const pontosTotais = atleticaRankings.reduce((total, r) => total + r.pontos, 0);
      const usuarioCount = new Set(atleticaRankings.map(r => r.usuarioId)).size; // Contagem única de usuários

      if (atleticaRankings.length > 0) {
        this.groupedRankings.push({
          atleticaId: atletica.id,
          nome: atletica.nome,
          pontosTotais: pontosTotais,
          usuarioCount: usuarioCount
        });
      }
    });
  }

  deleteRanking(id: number): void {
    this.rankingService.deleteRanking(id).subscribe(() => {
      this.loadRankings(); // Recarrega os rankings após a exclusão
    });
  }
}

