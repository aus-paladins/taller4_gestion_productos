import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import {
  Departamento,
  Categoria,
  ProductoRequest,
  ProductoResponse,
  VarianteProductoRequest,
  VarianteProductoResponse,
  VarianteListado,
  Atributo,
  ValorAtributo
} from '../models/producto.model';

@Injectable({
  providedIn: 'root'
})
export class ProductosService {

  private http = inject(HttpClient);

  private readonly apiUrl = 'http://localhost:8080/api';

  obtenerDepartamentos(): Observable<Departamento[]> {
    return this.http.get<Departamento[]>(
      `${this.apiUrl}/departamentos`
    );
  }

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

  crearVariante(
    variante: VarianteProductoRequest
  ): Observable<VarianteProductoResponse> {
    return this.http.post<VarianteProductoResponse>(
      `${this.apiUrl}/variantes`,
      variante
    );
  }

  obtenerVariantesListado(): Observable<VarianteListado[]> {
    return this.http.get<VarianteListado[]>(
      `${this.apiUrl}/variantes/listado`
    );
  }

  obtenerAtributos(): Observable<Atributo[]> {
    return this.http.get<Atributo[]>(
      `${this.apiUrl}/atributos`
    );
}

  obtenerValoresAtributo(): Observable<ValorAtributo[]> {
    return this.http.get<ValorAtributo[]>(
      `${this.apiUrl}/valores-atributo`
    );
}
}