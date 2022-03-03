package com.example.irrigationsystem.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity()
@Table(name = "time_slot_days")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlotDay implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;


//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "time_slot_Id")
//  @ToString.Exclude
//  @EqualsAndHashCode.Exclude
//  private TimeSlot timeSlot;

  private LocalDate startDate;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private LocalDateTime createdDate;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private LocalDateTime lastModifiedDate;


}
