import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MoviesInputComponent } from './movies-input.component';

describe('MoviesInputComponent', () => {
  let component: MoviesInputComponent;
  let fixture: ComponentFixture<MoviesInputComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MoviesInputComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MoviesInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
