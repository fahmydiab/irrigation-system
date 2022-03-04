import {PlotTimeSlot} from "./plot-time-slot.model";

export class Plot {
  id: number;
  crop: string;
  area: number;
  plotTimeSlots: PlotTimeSlot[];

  constructor(props = {}) {
    Object.assign(this, props);
  }
}
