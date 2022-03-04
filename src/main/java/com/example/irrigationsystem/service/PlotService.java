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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
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
        Plot savedPlot = plotRepo.save(plot);
//        createTimeSlots(savedPlot);
        return savedPlot;
    }

//    private void createTimeSlots(Plot savedPlot) {
//        LocalTime startTime = savedPlot.getStartTime();
//        Set<PlotTimeSlot> plotTimeSlots = savedPlot.getPlotTimeSlots();
//
//        PlotTimeSlot plotTimeSlot = new PlotTimeSlot();
//        for (Date day : timeSlot.getDays()) {
//            for (LocalTime startTime : timeSlot.getSlotTimeList()) {
//                plotTimeSlot.setStatus(PlotTimeSlotEnum.INIT);
//                plotTimeSlot.setPlot(savedPlot);
//                plotTimeSlot.setStartDateTime(LocalDateTime.of(day.getYear(), Month.of(day.getMonth()), day.getDay()
//                    , startTime.getHour(), startTime.getMinute()));
//                plotTimeSlotService.create(plotTimeSlot);
//            }
//        }
//    }

    @Transactional
    public Plot update(Plot plot) {
        Crop crop = plot.getCrop();
        if (crop.getId()==null)
        {
            Crop newCrop = cropService.create(crop);
            plot.setCrop(newCrop);
        }
        Set<PlotTimeSlot> slots = new HashSet<>();
        for (PlotTimeSlot slot : plot.getPlotTimeSlots()) {
            if(slot.getId()==null) {
                slot.setStatus(PlotTimeSlotEnum.INIT);
                plotTimeSlotService.create(slot);
                slots.add(slot);
            }
        }
        plot.setPlotTimeSlots(slots);
        Plot savedPlot = plotRepo.save(plot);
//        createTimeSlots(savedPlot);
        return savedPlot;
    }

    @Transactional
    public void delete(Integer id) {
        plotRepo.deleteById(id);
    }

    public Plot createPlot() {
        Plot plot = new Plot();
        plot.setArea(new BigDecimal(1000));
        return plotRepo.save(plot);
    }
}
