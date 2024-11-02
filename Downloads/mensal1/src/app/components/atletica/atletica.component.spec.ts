import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtleticaComponent } from './atletica.component';

describe('AtleticaComponent', () => {
  let component: AtleticaComponent;
  let fixture: ComponentFixture<AtleticaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AtleticaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AtleticaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
