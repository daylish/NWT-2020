import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowInputComponent } from './show-input.component';

describe('ShowInputComponent', () => {
  let component: ShowInputComponent;
  let fixture: ComponentFixture<ShowInputComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowInputComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
