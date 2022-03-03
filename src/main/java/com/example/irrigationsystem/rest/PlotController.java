package com.example.irrigationsystem.rest;

import com.example.irrigationsystem.dto.PlotDto;
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
    public List<PlotDto> list() {
        List<Plot> plots = plotService.list();
        return mapper.plotsToPlotDtos(plots);
    }

    @GetMapping("/{id}")
    public PlotDto getOne(@PathVariable("id") Integer id) {
        return mapper
                .plotToPlotDto(plotService.getOne(id));
    }

    @PostMapping("/{id}/time-slots")
    public void createTimeSlot(@PathVariable("id") Integer id, @RequestBody TimeSlot timeSlot) {
        PlotTimeSlot plotTimeSlot = new PlotTimeSlot();
        Plot plot = plotService.getOne(id);
        for (Date day : timeSlot.getDays()) {
            for (LocalTime startTime : timeSlot.getSlotTimeList()) {
                plotTimeSlot.setStatus(PlotTimeSlotEnum.INIT);
                plotTimeSlot.setPlot(plot);
                plotTimeSlot.setStartDateTime(LocalDateTime.of(day.getYear(), Month.of(day.getMonth()), day.getDay()
                        , startTime.getHour(), startTime.getMinute()));
                plotTimeSlotService.create(plotTimeSlot);
            }
        }

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
