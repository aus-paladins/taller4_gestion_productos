import { Component } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-lista-productos',
  imports: [
    ButtonModule,
    CurrencyPipe,
  ],
  templateUrl: './lista-productos.html',
  styleUrl: './lista-productos.scss',
})
export class ListaProductos {
  // Lista de datos simulando lo que vendría del backend
  productos = [
    {
      sku: 'CMP-T-L-NG',
      nombre: 'Campera Térmica',
      atributos: ['Talle L', 'Negro'],
      stock: 15,
      precio: 120000
    },
    {
      sku: 'CMP-T-M-AZ',
      nombre: 'Campera Térmica',
      atributos: ['Talle M', 'Azul'],
      stock: 2,
      precio: 120000
    }
  ];
}
