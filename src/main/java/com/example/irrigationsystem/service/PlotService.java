package com.example.irrigationsystem.service;

import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraph;
import com.cosium.spring.data.jpa.entity.graph.domain.EntityGraphs;
import com.example.irrigationsystem.filter.PlotFilter;
import com.example.irrigationsystem.model.Crop;
import com.example.irrigationsystem.model.Plot;
import com.example.irrigationsystem.repositories.PlotRepo;
import com.example.irrigationsystem.specification.PlotSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlotService {

    private final PlotRepo plotRepo;
    private final CropService cropService;
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
        return plotRepo.save(plot);
    }

    @Transactional
    public Plot update(Plot plot) {
        Crop crop = plot.getCrop();
        if (crop.getId()==null)
        {
            Crop newCrop = cropService.create(crop);
            plot.setCrop(newCrop);
        }
        return plotRepo.save(plot);
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
