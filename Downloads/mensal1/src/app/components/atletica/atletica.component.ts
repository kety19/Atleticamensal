import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { AtleticaService } from '../../services/atletica.service';
import { Atletica } from '../../models/atletica.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-atletica',
  standalone: true,
  imports: [CommonModule, FormsModule], 
  templateUrl: './atletica.component.html',
  styleUrls: ['./atletica.component.scss']
})
export class AtleticaComponent implements OnInit {
  atleticas: Atletica[] = [];
  newAtletica: Atletica = { id: 0, nome: '', universidade: '', descricao: '' };
  showCreatePostButton: boolean = false; // Controla a visibilidade do botão

  constructor(private atleticaService: AtleticaService, private router: Router) {}

  ngOnInit(): void {
    this.loadAtleticas();
  }

  loadAtleticas(): void {
    this.atleticaService.getAtleticas().subscribe(data => this.atleticas = data);
  }

  addAtletica(): void {
    this.atleticaService.createAtletica(this.newAtletica).subscribe(() => {
      this.loadAtleticas();
      this.newAtletica = { id: 0, nome: '', universidade: '', descricao: '' };
      this.showCreatePostButton = true; // Mostra o botão após o cadastro
    });
  }

  goToCreatePost(): void {
    this.router.navigate(['/postagem']); // Redireciona para a criação de postagens
  }

  deleteAtletica(id: number): void {
    this.atleticaService.deleteAtletica(id).subscribe(() => this.loadAtleticas());
  }
}
