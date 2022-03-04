
export class PlotTimeSlot {
  id: number;
  status: string;
  startDateTime: Date;

  constructor(props = {}) {
    Object.assign(this, props);
  }
}
