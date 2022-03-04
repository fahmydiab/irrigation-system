import {PlotTimeSlot} from "./plot-time-slot.model";

export class Plot {
  id: number;
  crop: Crop;
  area: number;
  plotTimeSlots: PlotTimeSlot[];

  constructor(props = {}) {
    Object.assign(this, props);
  }
}
interface Crop{
  name: string;
}
