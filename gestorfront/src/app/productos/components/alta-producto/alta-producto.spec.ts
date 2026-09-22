import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AltaProducto } from './alta-producto';

describe('AltaProducto', () => {
  let component: AltaProducto;
  let fixture: ComponentFixture<AltaProducto>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AltaProducto],
    }).compileComponents();

    fixture = TestBed.createComponent(AltaProducto);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
