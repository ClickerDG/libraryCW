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
public class Role extends BaseEntity {
  @Id
  @GeneratedValue
  @Column(name = "role_id")
  private Long id;

  @Column(name = "role_name", nullable = false, unique = true, length = 35)
  @Size(min = 3, max = 35)
  private String name;

  @ManyToMany(mappedBy = "roles")
  private Set<User> users;
}
