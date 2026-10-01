import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../../auth/auth.service';
import { FiltrosProductos } from '../filtros-productos/filtros-productos';
import { ListaProductos } from '../lista-productos/lista-productos';

@Component({
  selector: 'app-dashboard',
  imports: [FiltrosProductos, ListaProductos, RouterLink],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.scss'
})
export class Dashboard {
  readonly auth = inject(AuthService);
  private readonly router = inject(Router);

  salir(): void {
    this.auth.logout();
    this.router.navigate(['/']);
  }
}