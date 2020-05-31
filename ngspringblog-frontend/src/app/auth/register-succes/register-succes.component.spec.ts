import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterSuccesComponent } from './register-succes.component';

describe('RegisterSuccesComponent', () => {
  let component: RegisterSuccesComponent;
  let fixture: ComponentFixture<RegisterSuccesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterSuccesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterSuccesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
