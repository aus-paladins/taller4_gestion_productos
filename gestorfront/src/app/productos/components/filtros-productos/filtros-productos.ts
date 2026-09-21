import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CheckboxModule } from 'primeng/checkbox';
import { InputNumberModule } from 'primeng/inputnumber';
import { InputTextModule } from 'primeng/inputtext';
import { SelectModule } from 'primeng/select';

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
export class FiltrosProductos {
  departamentos = [
    { label: 'Todos los departamentos', value: null },
    { label: 'Ropa', value: 'ropa' },
    { label: 'Electrónica', value: 'electronica' },
    { label: 'Hogar', value: 'hogar' }
  ];

  opcionesOrden = [
    { label: 'Alfabético (A-Z)', value: 'alfabetico' },
    { label: 'Precio: Menor a Mayor', value: 'precio_asc' },
    { label: 'Precio: Mayor a Menor', value: 'precio_desc' }
  ];

  opcionesAgrupacion = [
  { label: 'Categoría', value: 'categoria' },
  { label: 'Departamento', value: 'departamento' },
  { label: 'Sin agrupar (Lista plana)', value: 'ninguno' }
  ];

  // Variables enlazadas a la vista
  busqueda: string = '';
  departamentoSeleccionado: any = null;
  precioMin: number | null = null;
  precioMax: number | null = null;
  soloConStock: boolean = false;
  ordenSeleccionado: any = 'alfabetico';
  agrupacionSeleccionada: string = 'categoria';
}
