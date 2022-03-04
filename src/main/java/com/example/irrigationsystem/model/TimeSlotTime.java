package com.example.irrigationsystem.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlotTime implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  private LocalTime startTime;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private LocalDateTime createdDate;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private LocalDateTime lastModifiedDate;


}
