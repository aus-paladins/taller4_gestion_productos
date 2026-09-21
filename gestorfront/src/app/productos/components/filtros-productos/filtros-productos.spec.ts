import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FiltrosProductos } from './filtros-productos';

describe('FiltrosProductos', () => {
  let component: FiltrosProductos;
  let fixture: ComponentFixture<FiltrosProductos>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FiltrosProductos],
    }).compileComponents();

    fixture = TestBed.createComponent(FiltrosProductos);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
