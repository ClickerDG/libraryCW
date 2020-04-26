package com.kozlovruzudzhenkkovalova.library.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Genre extends BaseEntity {

  @Id
  @GeneratedValue
  @Column(name = "genre_id")
  private Long id;

  @Column(name = "genre_name", length = 50)
  @Size(min = 1, max = 50)
  private String name;

  @Column(name = "genre_description")
  private String description;

  @ManyToMany(mappedBy = "genres")
  private Set<Edition> editions;


}
