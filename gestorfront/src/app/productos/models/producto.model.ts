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
  atributoIds: number[];
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
  productoId: number;
  sku: string;
  nombre: string;
  departamentoId: number;
  departamentoNombre: string;
  categoriaId: number;
  categoriaNombre: string;
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


export interface VarianteListado {
  id: number;
  sku: string;
  nombre: string;
  departamentoId: number;
  departamentoNombre: string;
  categoriaId: number;
  categoriaNombre: string;
  atributos: string[];
  stock: number;
  precio: number;
}

//Todos los campos son opcionales
export interface FiltroVariantes {
  busqueda: string;
  departamentoId: number | null;
  precioMin: number | null;
  precioMax: number | null;
  soloConStock: boolean;
  soloSinStock: boolean;
  mostrarInactivos: boolean;
  ordenarPor: 'alfabetico' | 'precio_asc' | 'precio_desc';
}
export const FILTRO_VACIO: FiltroVariantes = {
  busqueda: '',
  departamentoId: null,
  precioMin: null,
  precioMax: null,
  soloConStock: false,
  soloSinStock: false,
  mostrarInactivos: false,
  ordenarPor: 'alfabetico',
}

