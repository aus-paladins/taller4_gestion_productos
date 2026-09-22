import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { InputNumberModule } from 'primeng/inputnumber';
import { SelectModule } from 'primeng/select';
import { MessageModule } from 'primeng/message';

import {
  Categoria,
  ProductoRequest
} from '../../models/producto.model';

import { ProductosService } from '../../services/productos.service';

@Component({
  selector: 'app-alta-producto',
  imports: [
    ReactiveFormsModule,
    ButtonModule,
    InputTextModule,
    InputNumberModule,
    SelectModule,
    MessageModule
  ],
  templateUrl: './alta-producto.html',
  styleUrl: './alta-producto.scss',
})
export class AltaProducto implements OnInit {

  private formBuilder = inject(FormBuilder);
  private productosService = inject(ProductosService);
  private router = inject(Router);

  categorias: Categoria[] = [];

  errorCategorias = false;
  errorCreacion = false;
  productoCreado = false;

  productoForm = this.formBuilder.group({
    nombre: ['', Validators.required],
    description: ['', Validators.required],
    precioBase: [
      0,
      [
        Validators.required,
        Validators.min(0)
      ]
    ],
    activo: [true],
    categoriaId: [
      null as number | null,
      Validators.required
    ]
  });

  ngOnInit(): void {
    this.cargarCategorias();
  }

  cargarCategorias(): void {
    this.productosService.obtenerCategorias().subscribe({
      next: (categorias) => {
        this.categorias = categorias;
        this.errorCategorias = false;
      },
      error: (error) => {
        console.error('Error al cargar categorías:', error);
        this.errorCategorias = true;
      }
    });
  }

  crearProducto(): void {
    this.productoCreado = false;
    this.errorCreacion = false;

    if (this.productoForm.invalid) {
      this.productoForm.markAllAsTouched();
      return;
    }

    const valores = this.productoForm.getRawValue();

    const producto: ProductoRequest = {
      nombre: valores.nombre!,
      description: valores.description!,
      precioBase: valores.precioBase!,
      activo: valores.activo!,
      categoriaId: valores.categoriaId!
    };

    this.productosService.crearProducto(producto).subscribe({
      next: (respuesta) => {
        console.log('Producto creado:', respuesta);

        this.productoCreado = true;

        this.productoForm.reset({
          nombre: '',
          description: '',
          precioBase: 0,
          activo: true,
          categoriaId: null
        });
      },
      error: (error) => {
        console.error('Error al crear producto:', error);
        this.errorCreacion = true;
      }
    });
  }

  cancelar(): void {
    this.router.navigate(['/']);
  }

  esInvalido(campo: string): boolean {
    const control = this.productoForm.get(campo);

    return !!(
      control &&
      control.invalid &&
      (control.touched || control.dirty)
    );
  }
}