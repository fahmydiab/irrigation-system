package com.example.irrigationsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "plot_time_slots")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PlotTimeSlot extends Auditable{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private PlotTimeSlotEnum status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "plot_Id")
  @JsonBackReference
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Plot plot;

//  @ManyToOne
//  @JoinColumn(name = "time_slot_id")
//  @ToString.Exclude
//  @EqualsAndHashCode.Exclude
//  private TimeSlot timeSlot;

  private LocalDateTime startDateTime;
}
