import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaProductos } from './lista-productos';

describe('ListaProductos', () => {
  let component: ListaProductos;
  let fixture: ComponentFixture<ListaProductos>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListaProductos],
    }).compileComponents();

    fixture = TestBed.createComponent(ListaProductos);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
