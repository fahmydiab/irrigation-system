package com.example.irrigationsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PlotDto {
    private Integer id;
    private BigDecimal area;
    private CropDto crop;
    private String startTime;

}
