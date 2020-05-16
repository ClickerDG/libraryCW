package com.kozlovruzudzhenkkovalova.library.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity

public class EditionType implements Serializable {
  @Id
  @GeneratedValue
  @Column(name = "type_id")
  private Long id;

  @Column(name = "name_type")
  private String name;

  @OneToMany(mappedBy = "editionType", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, orphanRemoval = true)
  private Set<Edition> editions;
}
