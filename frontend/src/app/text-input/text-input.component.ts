import { AfterViewInit, Component, Renderer2, Input } from '@angular/core';
import { NgControl } from '@angular/forms';

import { InputBase } from '../input-base/input-base.component';

const numberValidator = (val: any) => !isNaN(val);
const mapToNumber = (val: any) => (numberValidator(val) ? Number(val) : val);

@Component({
  selector: 'app-text-input',
  templateUrl: './text-input.component.html',
  styleUrls: ['./text-input.component.css'],
})
export class TextInputComponent extends InputBase implements AfterViewInit {
  @Input()
  isNumber = false;

  validatros = [];

  @Input()
  textArea = false;

  @Input()
  forceNull = true;

  innerValue: any;

  @Input()
  mapper = (val: any) => val;

  constructor(renderer: Renderer2, ngControl: NgControl) {
    super(renderer);
    ngControl.valueAccessor = this;
    this.ngControl = ngControl;
  }

  ngAfterViewInit(): void {
    this.setOptions();
    // @ts-ignore
    this.validatros = [...(this.isNumber ? [numberValidator] : [])];
    this.mapper = this.isNumber ? mapToNumber : this.mapper;
  }

  writeValue(val: any) {
    this.value = val;
    this.innerValue = val;
  }


  get hasError() {
    return Object.keys(this.ngControl.errors || {}).length ? 'error' : '';
  }
}
