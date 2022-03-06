package com.example.irrigationsystem.service;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphs;
import com.example.irrigationsystem.filter.PlotFilter;
import com.example.irrigationsystem.model.Crop;
import com.example.irrigationsystem.model.Plot;
import com.example.irrigationsystem.model.PlotTimeSlot;
import com.example.irrigationsystem.model.PlotTimeSlotEnum;
import com.example.irrigationsystem.repositories.PlotRepo;
import com.example.irrigationsystem.specification.PlotSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PlotService {

    private final PlotRepo plotRepo;
    private final CropService cropService;
    private final PlotTimeSlotService plotTimeSlotService;
    private final PlotSpecification plotSpecification;

    public List<Plot> fetchAll(PlotFilter filter) {
        List<Plot> plots = plotRepo.findAll(plotSpecification.getFilter(filter),EntityGraphs.named("plot.details"));
        return plots;
    }

    public Plot getOne(Integer id) {
        EntityGraph entityGraph = EntityGraphs.named("plot.details");
        return plotRepo.findById(id, entityGraph);
    }

    @Transactional
    public Plot create(Plot plot) {
        plot.setCreatedDate(LocalDate.now());
        return updateOrCreatePlot(plot);

    }

    @Transactional
    public Plot update(Plot plot) {
        plot.setLastModifiedDate(LocalDate.now());
        return updateOrCreatePlot(plot);
    }

    private Plot updateOrCreatePlot(Plot plot) {
        Plot entity = new Plot();

        Crop crop = plot.getCrop();
        if (crop.getId()==null)
        {
            Crop newCrop = cropService.create(crop);
            entity.setCrop(newCrop);
        }
        entity.setArea(plot.getArea());
        Plot savedPlot = plotRepo.save(entity);

        Set<PlotTimeSlot> slots = new HashSet<>();
        for (PlotTimeSlot slot : plot.getPlotTimeSlots()) {
            if(slot.getId()==null) {
                slot.setPlot(savedPlot);
                slot.setStatus(PlotTimeSlotEnum.INIT);
                plotTimeSlotService.create(slot);
                slots.add(slot);
            }
        }
        savedPlot.setPlotTimeSlots(slots);
        plotRepo.save(savedPlot);
        return savedPlot;
    }

    @Transactional
    public void delete(Integer id) {
        plotRepo.deleteById(id);
    }

    public Plot createPlot() {
        Plot plot = new Plot(100);
        plot.setArea(new BigDecimal(1000));
        plot.setCrop(new Crop(1));
        return plotRepo.save(plot);
    }
}
