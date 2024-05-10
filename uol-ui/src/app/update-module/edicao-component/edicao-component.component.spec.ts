import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EdicaoComponentComponent } from './edicao-component.component';

describe('EdicaoComponentComponent', () => {
  let component: EdicaoComponentComponent;
  let fixture: ComponentFixture<EdicaoComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EdicaoComponentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EdicaoComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
