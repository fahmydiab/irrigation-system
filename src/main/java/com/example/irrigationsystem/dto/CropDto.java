package com.example.irrigationsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CropDto {
    private String name;
    private BigDecimal waterAmountPerAcre;
}
