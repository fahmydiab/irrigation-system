import {Component, OnInit} from '@angular/core';
import {PlotService} from "./services/plot.service";
import {Plot} from "./models/plot.model";
import {NzModalRef, NzModalService} from "ng-zorro-antd/modal";
import {CreatePlotComponent} from "./create-plot/create-plot.component";
import {NzSafeAny} from "ng-zorro-antd/core/types";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'frontend';
  plots: Plot[] = [];
  constructor(private plotService : PlotService,
              protected modalService: NzModalService) {
  }

  ngOnInit(): void {
    this.fetchPlots();
  }


  private fetchPlots() {
    this.plotService.listDetails().subscribe(
      plots => this.plots = plots
    );
  }

  edit(plot: Plot, $event: any) {
    const modal: NzModalRef<CreatePlotComponent, NzSafeAny> =
      this.modalService.create({
        nzTitle: 'Edit Plot',
        nzContent: CreatePlotComponent,
        nzComponentParams: { plot: { ...plot } },
        nzMaskClosable: false,
        nzOnCancel: () => modal.destroy(),
        nzOnOk: (component: CreatePlotComponent) => {
          component.onEdit().then(r => this.fetchPlots());
        }
      });

    $event.stopPropagation();
    return false;
  }
}
