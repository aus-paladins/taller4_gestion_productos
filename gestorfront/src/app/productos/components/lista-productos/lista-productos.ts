import { Component, OnInit, inject } from '@angular/core';
import { Router } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { CurrencyPipe } from '@angular/common';

import { VarianteListado } from '../../models/producto.model';
import { ProductosService } from '../../services/productos.service';

@Component({
  selector: 'app-lista-productos',
  imports: [
    ButtonModule,
    CurrencyPipe,
  ],
  templateUrl: './lista-productos.html',
  styleUrl: './lista-productos.scss',
})
export class ListaProductos implements OnInit {

  private router = inject(Router);
  private productosService = inject(ProductosService);

  productos: VarianteListado[] = [];
  cargando = false;
  error = false;

  ngOnInit(): void {
    this.cargarProductos();
  }

  cargarProductos(): void {
    this.cargando = true;
    this.error = false;

    this.productosService.obtenerVariantesListado().subscribe({
      next: (productos) => {
        this.productos = productos;
        this.cargando = false;
      },
      error: (error) => {
        console.error('Error al cargar productos:', error);
        this.error = true;
        this.cargando = false;
      }
    });
  }

  nuevoProducto(): void {
    this.router.navigate(['/productos/nuevo']);
  }

}
