package com.example.irrigationsystem.rest;

import com.example.irrigationsystem.dto.PlotDetailDto;
import com.example.irrigationsystem.dto.PlotDto;
import com.example.irrigationsystem.filter.PlotFilter;
import com.example.irrigationsystem.mapper.PlotMapper;
import com.example.irrigationsystem.model.Plot;
import com.example.irrigationsystem.model.PlotTimeSlot;
import com.example.irrigationsystem.model.PlotTimeSlotEnum;
import com.example.irrigationsystem.model.TimeSlot;
import com.example.irrigationsystem.service.PlotService;
import com.example.irrigationsystem.service.PlotTimeSlotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/plots")
@RequiredArgsConstructor
@Log
public class PlotController {

    private final PlotService plotService;
    private final PlotTimeSlotService plotTimeSlotService;
    private final PlotMapper mapper;

    @GetMapping
    public List<PlotDetailDto> list() {
        List<Plot> plots = plotService.fetchAll(new PlotFilter());
        return mapper.plotsToPlotDetailDtos(plots);
    }

    @GetMapping("/{id}")
    public PlotDetailDto getOne(@PathVariable("id") Integer id) {
        return mapper
                .plotToPlotDetailDto(plotService.getOne(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Plot create(@RequestBody Plot plot) {
        return plotService.create(plot);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Plot update(@RequestBody Plot plot) {
        return plotService.update(plot);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        plotService.delete(id);
    }
}
