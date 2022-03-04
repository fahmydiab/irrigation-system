import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MultiDatePicker } from './multi-data-picker';

describe('MultiDataPickerComponent', () => {
  let component: MultiDatePicker;
  let fixture: ComponentFixture<MultiDatePicker>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MultiDatePicker ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MultiDatePicker);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
