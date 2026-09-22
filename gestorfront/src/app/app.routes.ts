import { Routes } from '@angular/router';
import { AltaProducto } from './productos/components/alta-producto/alta-producto';

export const routes: Routes = [
  {
    path: 'productos/nuevo',
    component: AltaProducto
  }
];