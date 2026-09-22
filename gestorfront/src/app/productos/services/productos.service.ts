import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import {
  Categoria,
  ProductoRequest,
  ProductoResponse
} from '../models/producto.model';

@Injectable({
  providedIn: 'root'
})
export class ProductosService {

  private http = inject(HttpClient);

  private readonly apiUrl = 'http://localhost:8080/api';

  obtenerCategorias(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(
      `${this.apiUrl}/categorias`
    );
  }

  crearProducto(producto: ProductoRequest): Observable<ProductoResponse> {
    return this.http.post<ProductoResponse>(
      `${this.apiUrl}/productos`,
      producto
    );
  }
}