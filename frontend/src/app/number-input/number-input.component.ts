import { Component, AfterViewInit, Renderer2, Input } from '@angular/core';
import { NgControl } from '@angular/forms';
import {InputBase} from "../input-base/input-base.component";


@Component({
  selector: 'app-number-input',
  templateUrl: './number-input.component.html',
  styleUrls: ['./number-input.component.css'],
})
export class NumberInputComponent extends InputBase implements AfterViewInit {
  @Input()
  min = 1;

  @Input()
  max = Infinity;

  @Input()
  formatter: (value: number | string) => string | number = this
    .defaultFormatter;

  @Input()
  scale: false | number;

  @Input()
  price = false;

  priceFormatter = (value: number) =>
    `${Number(value).toLocaleString('en', {
      maximumFractionDigits: 2,
    })}`;

  constructor(renderer: Renderer2, ngControl: NgControl) {
    super(renderer);
    ngControl.valueAccessor = this;
    this.ngControl = ngControl;
    this.value = this.min;
  }

  ngAfterViewInit(): void {
    this.setOptions();
  }

  changes(value: any) {
    this.value = value;
    this.onChange(value);
  }

  defaultFormatter(val: any) {
    return val;
  }

}
