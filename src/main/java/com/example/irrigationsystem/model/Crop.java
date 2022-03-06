package com.example.irrigationsystem.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "crops")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Crop extends Auditable{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;

  @Column(scale = 2)
  private BigDecimal waterAmountPerAcre;

  public Crop(int i) {

  }
}
