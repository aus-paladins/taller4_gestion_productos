export interface Departamento {
  id: number;
  nombre: string;
}

export interface Categoria {
  id: number;
  nombre: string;
  departamentoId: number;
  departamentoNombre: string;
}

export interface ProductoRequest {
  nombre: string;
  description: string;
  precioBase: number;
  activo: boolean;
  categoriaId: number;
  atributoIds: number[];
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

export interface VarianteProductoResponse {
  id: number;
  sku: string;
  precioExtra: number;
  stock: number;
  productoId: number;
  productoNombre: string;
  valoresAtributoIds: number[];
}

export interface VarianteProductoRequest {
  sku: string;
  precioExtra: number;
  stock: number;
  productoId: number;
  valoresAtributoIds: number[];
}

export interface VarianteListado {
  id: number;
  sku: string;
  nombre: string;
  atributos: string[];
  stock: number;
  precio: number;
}

export interface Atributo {
  id: number;
  nombre: string;
}

export interface ValorAtributo {
  id: number;
  valor: string;
  atributoId: number;
  atributoNombre: string;
}