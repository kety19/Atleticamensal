import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.scss'],
  standalone: true,
  imports: [CommonModule, RouterModule]
})
export class InicioComponent {

  constructor(private router: Router) {}

  irParaCadastro() {
    this.router.navigate(['/usuario']);
  }
}
