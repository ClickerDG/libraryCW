package com.kozlovruzudzhenkkovalova.library.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class NewEdition {

  @Id
  private Long isbn;

  @OneToOne(cascade = CascadeType.ALL)
  @MapsId
  private Edition edition;

}
