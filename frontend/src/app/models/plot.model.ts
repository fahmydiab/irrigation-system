import {PlotTimeSlot} from "./plot-time-slot.model";

export class Plot {
  id: number;
  crop: Crop;
  area: number;
  plotTimeSlots: PlotTimeSlot[];
  startTime: string;

  constructor(props = {}) {
    Object.assign(this, props);
  }
}
interface Crop{
  name: string;
}
