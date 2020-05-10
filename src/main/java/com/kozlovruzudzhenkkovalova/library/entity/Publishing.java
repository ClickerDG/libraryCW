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
public class Publishing extends BaseEntity{
  @Id
  @GeneratedValue
  @Column(name = "publish_id")
  private Long id;

  @Column(name = "full_name")
  private String fullName;

  @OneToMany(mappedBy = "publishing", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private Set<Edition> editions;
}
