import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import {
  Departamento,
  Categoria,
  FiltroVariantes,
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

  obtenerVariantesListado(filtro?: Partial<FiltroVariantes>): Observable<VarianteListado[]> {
      let params = new HttpParams();
      if (filtro?.busqueda) {
        params = params.set('busqueda', filtro.busqueda);
      }
      if (filtro?.departamentoId != null) {
        params = params.set('departamentoId', filtro.departamentoId);
      }
      if (filtro?.precioMin != null) {
        params = params.set('precioMin', filtro.precioMin);
      }
      if (filtro?.precioMax != null) {
        params = params.set('precioMax', filtro.precioMax);
      }
      if (filtro?.soloConStock) {
        params = params.set('soloConStock', filtro.soloConStock);
      }
      if (filtro?.soloSinStock) {
        params = params.set('soloSinStock', filtro.soloSinStock);
      }
      if (filtro?.mostrarInactivos) {
        params = params.set('mostrarInactivos', filtro.mostrarInactivos);
      }
      if (filtro?.ordenarPor) {
        params = params.set('ordenarPor', filtro.ordenarPor);
      }

    return this.http.get<VarianteListado[]>(
      `${this.apiUrl}/variantes/listado`,
      { params }
    );
  }

}
