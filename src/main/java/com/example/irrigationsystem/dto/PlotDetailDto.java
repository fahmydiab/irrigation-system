package com.example.irrigationsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PlotDetailDto extends PlotBaseDto {
    private Integer id;
    private BigDecimal area;
    private CropDto crop;
    private List<PlotTimeSlotDto> plotTimeSlots;

}
