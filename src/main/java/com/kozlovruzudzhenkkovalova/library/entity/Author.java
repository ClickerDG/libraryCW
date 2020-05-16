package com.kozlovruzudzhenkkovalova.library.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author implements Serializable {

  @Id
  @GeneratedValue
  @Column(name = "auth_id")
  private Long id;

  @Column(name = "full_name")
  private String fullName;

  @JsonIgnoreProperties("authors")
  @ManyToMany(mappedBy = "authors")
  private Set<Edition> editions;
}
