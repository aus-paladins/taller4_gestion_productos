import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { FiltrosProductos } from './productos/components/filtros-productos/filtros-productos';
import { ListaProductos } from './productos/components/lista-productos/lista-productos';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, FiltrosProductos, ListaProductos],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('gestorfront');
}
