package com.example.irrigationsystem.mapper;

import com.example.irrigationsystem.dto.PlotDto;
import com.example.irrigationsystem.model.Plot;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlotMapper {

    PlotDto plotToPlotDto(Plot plot);

    List<PlotDto> plotsToPlotDtos(List<Plot> plots);

}
