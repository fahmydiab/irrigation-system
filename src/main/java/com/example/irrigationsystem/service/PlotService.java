package com.example.irrigationsystem.service;

import com.example.irrigationsystem.model.Plot;
import com.example.irrigationsystem.repositories.PlotRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlotService {

    private final PlotRepo plotRepo;

    public List<Plot> list() {
        List<Plot> plots = plotRepo.findAll();
        return plots;
    }

    public Plot getOne(Integer id) {
        return plotRepo.findById(id).orElse(null);
    }

    @Transactional
    public Plot create(Plot plot) {
        return plotRepo.save(plot);
    }

    @Transactional
    public Plot update(Plot plot) {
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
