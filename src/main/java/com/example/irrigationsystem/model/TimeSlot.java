package com.example.irrigationsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "time_slots")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TimeSlot implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(scale = 2)
  private BigDecimal waterAmountPerSlot;

  @OneToMany(mappedBy = "timeSlot", fetch = FetchType.LAZY)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @JsonBackReference("timeSlot-plotTimeSlot")
  private List<PlotTimeSlot> plotTimeSlots;

  @OneToMany(mappedBy = "timeSlot", fetch = FetchType.LAZY)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private List<TimeSlotDay> days = new ArrayList<>();

  @OneToMany(mappedBy = "timeSlot", fetch = FetchType.LAZY)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private List<TimeSlotTime> slotTimeList = new ArrayList<>();

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private LocalDateTime createdAt;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private LocalDateTime updatedAt;

}
