import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

import {Plot} from "../models/plot.model";
import {PlotService} from "../services/plot.service";

@Component({
  selector: 'app-create-unit',
  templateUrl: './create-plot.component.html',
})
export class CreatePlotComponent
  implements OnInit
{
  form: FormGroup;
  plot: Plot = new Plot();
  constructor(private fb: FormBuilder,
              private plotService: PlotService) {}

  ngOnInit() {

    this.form = this.fb.group({
      crop: [this.plot.crop?.name],
      area: [
        this.plot.area,
        [Validators.required, Validators.min(0)],
      ],
      plotTimeSlots: [this.plot.plotTimeSlots],
    });
  }

  onSubmit(): Promise<any> {
    if (this.form.valid) {
      return this.plotService.create(this.form.value).toPromise();
    } else {
      this.setFieldDirty();
      return Promise.reject(false);
    }
  }

  onEdit() {
    if (this.form.valid) {
      const newPlot = { ...this.plot, ...this.form.value };
      newPlot.crop = {name:this.form.controls['crop'].value}
      return this.plotService
        .update(newPlot)
        .toPromise()
        .then(() => newPlot);
    } else {
      this.form.controls.area.markAsDirty();
      this.form.controls.crop.markAsDirty();
      return Promise.reject(false);
    }
  }
  setFieldDirty() {
    Object.keys(this.form.controls).forEach(key => {
      this.form.get(key)?.markAsDirty();
    });
  }
}
