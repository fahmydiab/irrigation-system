package com.example.irrigationsystem.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlotDay implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

  private LocalDate startDate;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private LocalDateTime createdDate;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private LocalDateTime lastModifiedDate;


}
