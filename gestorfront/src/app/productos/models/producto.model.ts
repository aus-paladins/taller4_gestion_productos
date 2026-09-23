export interface Categoria {
  id: number;
  nombre: string;
}

export interface ProductoRequest {
  nombre: string;
  description: string;
  precioBase: number;
  activo: boolean;
  categoriaId: number;
}

export interface ProductoResponse {
  id: number;
  nombre: string;
  description: string;
  precioBase: number;
  activo: boolean;
  categoriaId: number;
  categoriaNombre: string;
}

// Coincide 1 a 1 con VarianteListadoDTO del back (GET /api/variantes/listado)
export interface VarianteListado {
  id: number;
  sku: string;
  nombre: string;
  atributos: string[];
  stock: number;
  precio: number;
}
