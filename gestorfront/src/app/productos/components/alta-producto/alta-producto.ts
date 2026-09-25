import { Component, OnInit, inject } from '@angular/core';
import {
  FormBuilder,
  FormsModule,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';
import { Router } from '@angular/router';

import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { InputNumberModule } from 'primeng/inputnumber';
import { SelectModule } from 'primeng/select';
import { MultiSelectModule } from 'primeng/multiselect';
import { MessageModule } from 'primeng/message';

import {
  Departamento,
  Categoria,
  ProductoRequest,
  VarianteProductoRequest,
  Atributo,
  ValorAtributo
} from '../../models/producto.model';

import { ProductosService } from '../../services/productos.service';

@Component({
  selector: 'app-alta-producto',
  imports: [
    ReactiveFormsModule,
    FormsModule,
    ButtonModule,
    InputTextModule,
    InputNumberModule,
    SelectModule,
    MultiSelectModule,
    MessageModule
  ],
  templateUrl: './alta-producto.html',
  styleUrl: './alta-producto.scss',
})
export class AltaProducto implements OnInit {

  private formBuilder = inject(FormBuilder);
  private productosService = inject(ProductosService);
  private router = inject(Router);

  departamentos: Departamento[] = [];
  categorias: Categoria[] = [];

  atributos: Atributo[] = [];
  valoresAtributo: ValorAtributo[] = [];
  valoresSeleccionados: { [atributoId: number]: number | null } = {};

  categoriasFiltradas: Categoria[] = [];

  errorDepartamentos = false;
  errorCategorias = false;
  errorCreacion = false;

  productoCreado = false;

  productoForm = this.formBuilder.group({

    // -------------------------
    // PRODUCTO
    // -------------------------

    departamentoId: [
      null as number | null,
      Validators.required
    ],

    categoriaId: [
      null as number | null,
      Validators.required
    ],

    nombre: [
      '',
      Validators.required
    ],

    description: [
      '',
      Validators.required
    ],

    precioBase: [
      0,
      [
        Validators.required,
        Validators.min(0)
      ]
    ],

    activo: [true],

    // Atributos que tendrá el producto
    atributoIds: [
      [] as number[]
    ],

    // -------------------------
    // VARIANTE
    // -------------------------

    sku: [
      '',
      Validators.required
    ],

    stock: [
      0,
      [
        Validators.required,
        Validators.min(0)
      ]
    ],

    precioExtra: [
      0,
      [
        Validators.required,
        Validators.min(0)
      ]
    ]

  });

  ngOnInit(): void {
    this.cargarDepartamentos();
    this.cargarCategorias();
    this.cargarAtributos();
    this.cargarValoresAtributo();

    this.productoForm
      .get('departamentoId')
      ?.valueChanges
      .subscribe(departamentoId => {

        this.filtrarCategorias(departamentoId);

        // Cuando cambia el departamento, la categoría anterior deja de ser válida.
        this.productoForm
          .get('categoriaId')
          ?.setValue(null);
      });

    this.productoForm
      .get('atributoIds')
      ?.valueChanges
      .subscribe(atributoIds => {

        this.valoresSeleccionados = {};

        for (const atributoId of atributoIds ?? []) {
          this.valoresSeleccionados[atributoId] = null;
        }
      });
  }

  cargarDepartamentos(): void {

    this.productosService.obtenerDepartamentos().subscribe({

      next: (departamentos) => {
        this.departamentos = departamentos;
        this.errorDepartamentos = false;
      },

      error: (error) => {
        console.error(
          'Error al cargar departamentos:',
          error
        );

        this.errorDepartamentos = true;
      }

    });
  }

  cargarCategorias(): void {

    this.productosService.obtenerCategorias().subscribe({

      next: (categorias) => {
        this.categorias = categorias;
        this.errorCategorias = false;
      },

      error: (error) => {
        console.error(
          'Error al cargar categorías:',
          error
        );

        this.errorCategorias = true;
      }

    });
  }

  cargarAtributos(): void {

    this.productosService.obtenerAtributos().subscribe({

      next: (atributos) => {
        this.atributos = atributos;
      },

      error: (error) => {
        console.error(
          'Error al cargar atributos:',
          error
        );
      }

    });
  }

  cargarValoresAtributo(): void {

    this.productosService.obtenerValoresAtributo().subscribe({

      next: (valores) => {
        this.valoresAtributo = valores;
      },

      error: (error) => {
        console.error(
          'Error al cargar valores de atributos:',
          error
        );
      }

    });
  }

  valoresDeAtributo(atributoId: number): ValorAtributo[] {
    return this.valoresAtributo.filter(
      valor => valor.atributoId === atributoId
    );
  }

  nombreAtributo(atributoId: number): string {
    return this.atributos.find(
      atributo => atributo.id === atributoId
    )?.nombre ?? '';
  }

  filtrarCategorias(
    departamentoId: number | null
  ): void {

    if (departamentoId === null) {
      this.categoriasFiltradas = [];
      return;
    }

    this.categoriasFiltradas =
      this.categorias.filter(
        categoria =>
          categoria.departamentoId === departamentoId
      );
  }

  crearProducto(): void {

    this.productoCreado = false;
    this.errorCreacion = false;

    if (this.productoForm.invalid) {

      this.productoForm.markAllAsTouched();

      return;
    }

    const valores =
      this.productoForm.getRawValue();

    const producto: ProductoRequest = {

      nombre: valores.nombre!,

      description: valores.description!,

      precioBase: valores.precioBase!,

      activo: valores.activo!,

      categoriaId: valores.categoriaId!,

      atributoIds: valores.atributoIds ?? []

    };

    this.productosService
      .crearProducto(producto)
      .subscribe({

        next: (productoCreado) => {

          console.log(
            'Producto creado:',
            productoCreado
          );

          this.crearVariante(
            productoCreado.id
          );
        },

        error: (error) => {

          console.error(
            'Error al crear producto:',
            error
          );

          this.errorCreacion = true;
        }

      });
  }

  private crearVariante(
    productoId: number
  ): void {

    const valores =
      this.productoForm.getRawValue();

    // Convertimos los valores seleccionados en una lista de IDs
    // para enviarlos en el formato esperado por el backend.
    const valoresAtributoIds = Object.values(
      this.valoresSeleccionados
    ).filter(
      (id): id is number => id !== null
    );

    const variante: VarianteProductoRequest = {

      sku: valores.sku!,

      precioExtra: valores.precioExtra!,

      stock: valores.stock!,

      productoId: productoId,

      valoresAtributoIds: valoresAtributoIds

    };

    this.productosService
      .crearVariante(variante)
      .subscribe({

        next: (varianteCreada) => {

          console.log(
            'Variante creada:',
            varianteCreada
          );

          this.productoCreado = true;

          this.limpiarFormulario();
        },

        error: (error) => {

          console.error(
            'Error al crear variante:',
            error
          );

          this.errorCreacion = true;
        }

      });
  }

  private limpiarFormulario(): void {

    this.productoForm.reset({

      departamentoId: null,

      categoriaId: null,

      nombre: '',

      description: '',

      precioBase: 0,

      activo: true,

      atributoIds: [],

      sku: '',

      stock: 0,

      precioExtra: 0

    });

    this.valoresSeleccionados = {};

    this.categoriasFiltradas = [];
  }

  cancelar(): void {
    this.router.navigate(['/']);
  }

  esInvalido(campo: string): boolean {

    const control =
      this.productoForm.get(campo);

    return !!(
      control &&
      control.invalid &&
      (control.touched || control.dirty)
    );
  }
}