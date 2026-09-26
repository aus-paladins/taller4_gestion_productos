import { Component, signal, inject } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';

import { FiltrosProductos } from './productos/components/filtros-productos/filtros-productos';
import { ListaProductos } from './productos/components/lista-productos/lista-productos';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, FiltrosProductos, ListaProductos],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  private router = inject(Router);

  protected readonly title = signal('gestorfront');

    get esAltaProducto(): boolean {
      return this.router.url === '/productos/nuevo' || this.router.url.startsWith('/productos/editar/');
    }  
}
