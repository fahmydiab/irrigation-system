package com.example.irrigationsystem.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TimeSlot implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(scale = 2)
  private BigDecimal waterAmountPerSlot;


  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @ElementCollection
  private List<Date> days = new ArrayList<>();

  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @ElementCollection
  private List<LocalTime> slotTimeList = new ArrayList<>();

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private LocalDateTime createdDate;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private LocalDateTime lastModifiedDate;

}
