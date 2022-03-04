import {
  ElementRef,
  Input,
  Renderer2,
  ViewChild,
  Output,
  EventEmitter, Directive,
} from '@angular/core';
import { ControlValueAccessor, NgControl } from '@angular/forms';

@Directive()
export abstract class InputBase implements ControlValueAccessor {
  @Input()
  name = '';

  @Input()
  options: any = {};

  @Input()
  attrs: any = {};

  @Input()
  errors: any;

  @Input()
  required = false;

  @Input()
  isRequired = false;

  @Input()
  itemClass = '';

  @Input()
  disabled = false;


  @Input()
  placeholder = '';

  @Input()
  flat = false;

  @Input()
  allowClear = true;

  @Output()
  change = new EventEmitter();

  @ViewChild('ref')
  ref: ElementRef;

  value: number;
  onChange: (value: any) => {};
  onTouch: () => {};
  ngControl: NgControl;

  constructor(private renderer: Renderer2) {}

  writeValue(obj: any): void {
    this.value = obj;
  }

  registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouch = fn;
  }

  setDisabledState?(isDisabled: boolean): void {
    this.disabled = isDisabled;
  }

  setOptions() {
    if (this.ref) {
      const ele = this.ref.nativeElement;
      Object.keys(this.attrs).forEach((key, value) => {
        this.renderer.setAttribute(ele, key, this.attrs[key]);
      });
    }
  }

  hasRequiredError() {
    return (
      this.ngControl.errors &&
      this.ngControl.errors.required &&
      this.ngControl.touched
    );
  }

}
