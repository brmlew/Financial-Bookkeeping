import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DateBalanceComponent } from './date-balance.component';

describe('DateBalanceComponent', () => {
  let component: DateBalanceComponent;
  let fixture: ComponentFixture<DateBalanceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DateBalanceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DateBalanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
