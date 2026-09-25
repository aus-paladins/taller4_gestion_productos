import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CheckboxModule } from 'primeng/checkbox';
import { InputNumberModule } from 'primeng/inputnumber';
import { InputTextModule } from 'primeng/inputtext';
import { SelectModule } from 'primeng/select';
import { Subject } from 'rxjs';
import { debounceTime, takeUntil } from 'rxjs/operators';

import { Departamento, FiltroVariantes } from '../../models/producto.model';
import { ProductosService } from '../../services/productos.service';
import { FiltrosVariantesService } from '../../services/filtros-variantes.service';

@Component({
  selector: 'app-filtros-productos',
  imports: [
    FormsModule,
    CheckboxModule,
    InputNumberModule,
    InputTextModule,
    SelectModule,
  ],
  templateUrl: './filtros-productos.html',
  styleUrl: './filtros-productos.scss',
})
export class FiltrosProductos implements OnInit, OnDestroy {

  private productosService = inject(ProductosService);
  private filtrosService = inject(FiltrosVariantesService);

  departamentos: Departamento[] = [];

  opcionesOrden = [
    { label: 'Alfabético (A-Z)', value: 'alfabetico' },
    { label: 'Precio: Menor a Mayor', value: 'precio_asc' },
    { label: 'Precio: Mayor a Menor', value: 'precio_desc' }
  ];

  // Variables enlazadas a la vista
  busqueda: string = '';
  departamentoSeleccionado: any = null;
  precioMin: number | null = null;
  precioMax: number | null = null;
  soloConStock: boolean = false;
  soloSinStock: boolean = false;
  mostrarInactivos: boolean = false;
  ordenSeleccionado: FiltroVariantes['ordenarPor'] = 'alfabetico';

  // Subject de RxJS para recibir eventos
  private destroy$ = new Subject<void>();
  // canal de comunicación de eventos, amortigüa consulta inmediata
  private cambiosConDebounce$ = new Subject<Partial<FiltroVariantes>>();

  ngOnInit(): void {
    this.productosService.obtenerDepartamentos().subscribe({
      next: (departamentos) => this.departamentos = departamentos,
      error: (err) => console.error('Error al cargar departamentos:', err),
    });

    this.cambiosConDebounce$.pipe(
      debounceTime(600),
      takeUntil(this.destroy$),
    ).subscribe((cambios) => this.filtrosService.actualizar(cambios));
  }
  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  // --- Con debounce (se tipean) ---
  onBusquedaChange(valor: string): void {
    this.cambiosConDebounce$.next({ busqueda: valor });
  }
  onPrecioMinChange(valor: number | null): void {
    this.cambiosConDebounce$.next({ precioMin: valor });
  }
  onPrecioMaxChange(valor: number | null): void {
    this.cambiosConDebounce$.next({ precioMax: valor });
  }

  // --- Inmediatos (una sola selección/click) ---
  onDepartamentoChange(valor: number | null): void {
    this.filtrosService.actualizar({ departamentoId: valor });
  }
  onStockChange(valor: boolean): void {
    this.soloConStock = valor;
    // Mutuamente excluyentes: pedir stock > 0 y stock <= 0 a la vez
    // siempre da resultado vacío, así que al marcar uno se desmarca el otro.
    if (valor) {
      this.soloSinStock = false;
    }
    this.filtrosService.actualizar({
      soloConStock: this.soloConStock,
      soloSinStock: this.soloSinStock,
    });
  }
  onSinStockChange(valor: boolean): void {
    this.soloSinStock = valor;
    if (valor) {
      this.soloConStock = false;
    }
    this.filtrosService.actualizar({
      soloConStock: this.soloConStock,
      soloSinStock: this.soloSinStock,
    });
  }
  onOrdenChange(valor: FiltroVariantes['ordenarPor']): void {
    this.filtrosService.actualizar({ ordenarPor: valor });
  }
  onMostrarInactivosChange(valor: boolean): void {
    this.filtrosService.actualizar({ mostrarInactivos: valor });
  }

  limpiarFiltros(): void {
    this.busqueda = '';
    this.departamentoSeleccionado = null;
    this.precioMin = null;
    this.precioMax = null;
    this.soloConStock = false;
    this.soloSinStock = false;
    this.mostrarInactivos = false;
    this.ordenSeleccionado = 'alfabetico';
    this.filtrosService.limpiar();
  }
}
