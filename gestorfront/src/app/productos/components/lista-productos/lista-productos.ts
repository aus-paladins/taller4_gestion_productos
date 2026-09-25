import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { Router } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { CurrencyPipe } from '@angular/common';
import { toObservable } from '@angular/core/rxjs-interop';
import { Subject, of } from 'rxjs';
import { catchError, switchMap, takeUntil } from 'rxjs/operators';

import { VarianteListado } from '../../models/producto.model';
import { ProductosService } from '../../services/productos.service';
import { FiltrosVariantesService } from '../../services/filtros-variantes.service';

interface GrupoDepartamento {
  departamentoId: number;
  departamentoNombre: string;
  categorias: GrupoCategoria[];
}
interface GrupoCategoria {
  categoriaId: number;
  categoriaNombre: string;
  items: VarianteListado[];
}

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
  private filtrosService = inject(FiltrosVariantesService);

  grupos: GrupoDepartamento[] = [];
  cargando = false;
  error = false;

  private destroy$ = new Subject<void>();

  ngOnInit(): void {
    // Reacciona a cada cambio del filtro compartido
    toObservable(this.filtrosService.filtro).pipe(
      switchMap((filtro) => {
        this.cargando = true;
        this.error = false;

        return this.productosService.obtenerVariantesListado(filtro).pipe(
          catchError((err) => {
            console.error('Error al cargar productos:', err);
            this.error = true;
            return of<VarianteListado[]>([]);
          })
        );
      }),
      takeUntil(this.destroy$),
    ).subscribe((productos) => {
      this.grupos = this.agruparPorDepartamentoYCategoria(productos);
      this.cargando = false;
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  nuevoProducto(): void {
    this.router.navigate(['/productos/nuevo']);
  }

  editarProducto(producto: VarianteListado): void {
    this.router.navigate(['/productos/editar', producto.productoId, producto.id]);
  }

  private agruparPorDepartamentoYCategoria(
    productos: VarianteListado[]
  ): GrupoDepartamento[] {
    const departamentos = new Map<number, GrupoDepartamento>();

    for (const producto of productos) {
      let departamento = departamentos.get(producto.departamentoId);

      if (!departamento) {
        departamento = {
          departamentoId: producto.departamentoId,
          departamentoNombre: producto.departamentoNombre,
          categorias: [],
        };

        departamentos.set(producto.departamentoId, departamento);
      }

      let categoria = departamento.categorias.find(
        (c) => c.categoriaId === producto.categoriaId
      );

      if (!categoria) {
        categoria = {
          categoriaId: producto.categoriaId,
          categoriaNombre: producto.categoriaNombre,
          items: [],
        };

        departamento.categorias.push(categoria);
      }

      categoria.items.push(producto);
    }

    return Array.from(departamentos.values());
  }
}

