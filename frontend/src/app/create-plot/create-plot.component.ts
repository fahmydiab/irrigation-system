import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

import {Plot} from "../models/plot.model";
import {PlotService} from "../services/plot.service";
import {NgbDateStruct} from "@ng-bootstrap/ng-bootstrap";
import {PlotTimeSlot} from "../models/plot-time-slot.model";

@Component({
  selector: 'app-create-unit',
  templateUrl: './create-plot.component.html',
})
export class CreatePlotComponent
  implements OnInit,OnDestroy {
  form: FormGroup;
  time: string;
  plot: Plot = new Plot();

  constructor(private fb: FormBuilder,
              private plotService: PlotService) {
  }

  datesSelected: NgbDateStruct[] = [];

  change(value: NgbDateStruct[]) {
    this.datesSelected = value;
  }

  ngOnInit() {
    this.plot.plotTimeSlots.forEach(slot =>{
      let date = slot.startDateTime;
      let year = date.getFullYear();
      let month = date.getMonth();
      let day = date.getDay();
      this.datesSelected.push(
        {
          year: year,
          month: month,
          day: day
        }
      )
    });

    this.form = this.fb.group({
      crop: [this.plot.crop?.name],
      area: [
        this.plot.area,
        [Validators.required, Validators.min(0)],
      ],
      startTime: [this.plot.startTime],
    });
  }

  fillTimeSlotDays() {
    this.datesSelected.forEach(
      date => {
        const slot = new PlotTimeSlot();
        slot.startDateTime = new Date(`${date.month} ${date.day}  ${date.year} ${this.form.value.startTime}`);
        this.plot.plotTimeSlots.push(slot);
      }
    )
  }

  onSubmit(): Promise<any> {
    this.fillTimeSlotDays();
    if (this.form.valid) {
      return this.plotService.create(this.form.value).toPromise();
    } else {
      this.setFieldDirty();
      return Promise.reject(false);
    }
  }

  onEdit() {
    this.fillTimeSlotDays();
    if (this.form.valid) {
      const newPlot = {...this.plot, ...this.form.value};
      newPlot.crop = {name: this.form.controls['crop'].value}
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

  ngOnDestroy(): void {
    this.datesSelected = [];
  }
}
