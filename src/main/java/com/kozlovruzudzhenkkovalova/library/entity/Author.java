package com.kozlovruzudzhenkkovalova.library.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Author extends BaseEntity {

  @Id
  @GeneratedValue
  @Column(name = "auth_id")
  private Long id;

  @Column(name = "full_name")
  private String fullName;

  @ManyToMany(mappedBy = "authors")
  private Set<Edition> editions;
}
