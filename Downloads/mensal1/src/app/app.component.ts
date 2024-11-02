import { Component } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';

import { MdbCollapseModule } from 'mdb-angular-ui-kit/collapse';
import { MenuComponent } from "./components/menu/menu.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule, MdbCollapseModule, MenuComponent], 
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'mensal1';
}
