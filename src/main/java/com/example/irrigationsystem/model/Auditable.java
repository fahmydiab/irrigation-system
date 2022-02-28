package com.example.irrigationsystem.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

  @CreatedDate
  @Column(updatable = false)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @Temporal(TIMESTAMP)
  private Date createdDate;


  @LastModifiedDate
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @Temporal(TIMESTAMP)
  private Date lastModifiedDate;
}
