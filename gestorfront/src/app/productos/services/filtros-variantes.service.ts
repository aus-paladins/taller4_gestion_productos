import { Injectable, signal } from '@angular/core';
import { FiltroVariantes, FILTRO_VACIO } from '../models/producto.model';

// Estado compartido: FiltrosProductos escribe, ListaProductos lee (reactivamente, vía signal).
@Injectable({
  providedIn: 'root'
})
export class FiltrosVariantesService {

  readonly filtro = signal<FiltroVariantes>({ ...FILTRO_VACIO });

  actualizar(cambios: Partial<FiltroVariantes>): void {
    this.filtro.update(actual => ({ ...actual, ...cambios }));
  }

  limpiar(): void {
    this.filtro.set({ ...FILTRO_VACIO });
  }
}

