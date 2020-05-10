package com.kozlovruzudzhenkkovalova.library.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Edition extends BaseEntity{

  @Id
  @GeneratedValue
  @Column(name = "isbn")
  private Long id;

  @Column(name = "edition_name", nullable = false)
  private String name;

  @Column(name = "edition_year", length = 10)
  @Size(max = 10)
  private String year;

  @ManyToOne
  @JoinColumn(name = "type_id", nullable = false)
  private EditionType editionType;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
      name = "genre_edition",
      joinColumns = {@JoinColumn(name = "isbn")},
      inverseJoinColumns = {@JoinColumn(name = "genre_id")}
  )
  private Set<Genre> genres;

  @ManyToOne
  @JoinColumn(name = "publish_id", nullable = false)
  private Publishing publishing;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
      name = "author_edition",
      joinColumns = {@JoinColumn(name = "isbn")},
      inverseJoinColumns = {@JoinColumn(name = "author_id")}
  )
  private Set<Author> authors;

  @OneToMany(mappedBy = "edition", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private Set<Review> reviews;

  @OneToMany(mappedBy = "edition", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private Set<RentedEdition> rentedEditions;

}
