package com.kozlovruzudzhenkkovalova.library.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class RentedEdition extends BaseEntity {
  @Id
  @GeneratedValue
  @Column(name = "rent_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "isbn", nullable = false)
  private Edition edition;

  @ManyToOne
  @JoinColumn(name = "reader_id", nullable = false)
  private User user;


}
