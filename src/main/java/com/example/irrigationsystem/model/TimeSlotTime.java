package com.example.irrigationsystem.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

//@Entity()
//@Table(name = "time_slot_start_times")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlotTime implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;

//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "time_slot_Id")
//  @ToString.Exclude
//  @EqualsAndHashCode.Exclude
//  private TimeSlot timeSlot;

  private LocalTime startTime;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private LocalDateTime createdDate;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private LocalDateTime lastModifiedDate;


}
