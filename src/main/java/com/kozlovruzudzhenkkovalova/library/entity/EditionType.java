package com.kozlovruzudzhenkkovalova.library.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class EditionType extends BaseEntity {
  @Id
  @GeneratedValue
  @Column(name = "type_id")
  private Long id;

  @Column(name = "name_type")
  private String name;

  @OneToMany(mappedBy = "editionType", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private Set<Edition> editions;
}
