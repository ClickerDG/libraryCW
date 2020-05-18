package com.kozlovruzudzhenkkovalova.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "reader")
public class User implements Serializable {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  @Column(name = "reader_id", nullable = false)
  private Long id;

  @Column(name = "full_name", length = 100)
  @Size(min = 3, max = 100)
  private String fullName;

  @Column(name = "phone", length = 15)
  @Size(min = 4, max = 15)
  private String phoneNumber;

  @Column(name = "login", unique = true, length = 25)
  @Size(min = 5, max = 25)
  private String username;

  @Column(name = "password")
  private String password;

  @JsonIgnoreProperties("users")
  @ManyToMany(cascade = CascadeType.MERGE)
  @JoinTable(
      name = "role_user",
      joinColumns = {@JoinColumn(name = "reader_id")},
      inverseJoinColumns = {@JoinColumn(name = "role_id")}
  )
  Set<Role> roles;

  @JsonIgnoreProperties("user")
  @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, orphanRemoval = true)
  private Set<Review> reviews;

  @JsonIgnoreProperties("user")
  @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, orphanRemoval = true)
  private Set<RentedEdition> rentedEditions;
}
