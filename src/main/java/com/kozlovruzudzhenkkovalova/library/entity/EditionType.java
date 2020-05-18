package com.kozlovruzudzhenkkovalova.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity

public class EditionType implements Serializable {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  @Column(name = "type_id", nullable = false)
  private Long id;

  @Column(name = "name_type")
  private String name;

  @JsonIgnoreProperties("editionType")
  @OneToMany(mappedBy = "editionType", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, orphanRemoval = true)
  private Set<Edition> editions;
}
