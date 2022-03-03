package com.example.irrigationsystem.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlotFilter {
    private Integer areaFrom;
    private Integer areaTo;
    private Integer cropFrom;
    private Integer cropTo;
}
