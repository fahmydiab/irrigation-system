package com.example.irrigationsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlotTimeSlotDto {
    private Integer id;
    private String status;
    private String startDateTime;
}
