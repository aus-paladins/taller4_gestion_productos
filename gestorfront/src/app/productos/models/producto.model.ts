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