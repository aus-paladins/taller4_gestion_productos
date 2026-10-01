import { Routes } from '@angular/router';
import { adminGuard } from './auth/admin.guard';

export const routes: Routes = [
  { path: '', loadComponent: () => import('./auth/auth-page').then(module => module.AuthPage) },
  { path: 'login', loadComponent: () => import('./auth/auth-page').then(module => module.AuthPage) },
  { path: 'register', loadComponent: () => import('./auth/auth-page').then(module => module.AuthPage) },
  { path: 'productos', loadComponent: () => import('./productos/components/dashboard/dashboard').then(module => module.Dashboard), pathMatch: 'full' },
  {
    path: 'productos/nuevo',
    loadComponent: () => import('./productos/components/alta-producto/alta-producto').then(module => module.AltaProducto),
    canActivate: [adminGuard]
  },
  {
    path: 'productos/editar/:productoId/:varianteId',
    loadComponent: () => import('./productos/components/alta-producto/alta-producto').then(module => module.AltaProducto),
    canActivate: [adminGuard]
  },
  { path: '**', redirectTo: '' }
];