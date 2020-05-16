package com.kozlovruzudzhenkkovalova.library.entity;

import lombok.Getter;
import lombok.Setter;

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
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
public class Edition implements Serializable {

  @Id
  @GeneratedValue
  @Column(name = "isbn")
  private Long id;

  @Column(name = "edition_name", nullable = false)
  private String name;

  @Column(name = "edition_year", length = 10)
  @Size(max = 10)
  private String year;

  @Column(name = "image_url")
  private String imageUrl;

  @ManyToOne
  @JoinColumn(name = "type_id", nullable = false)
  private EditionType editionType;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(
      name = "genre_edition",
      joinColumns = {@JoinColumn(name = "isbn")},
      inverseJoinColumns = {@JoinColumn(name = "genre_id")}
  )
  private Set<Genre> genres;

  @ManyToOne
  @JoinColumn(name = "publish_id", nullable = false)
  private Publishing publishing;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(
      name = "author_edition",
      joinColumns = {@JoinColumn(name = "isbn")},
      inverseJoinColumns = {@JoinColumn(name = "author_id")}
  )
  private Set<Author> authors;

  @OneToMany(mappedBy = "edition", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
  private Set<Review> reviews;

  @OneToMany(mappedBy = "edition", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
  private Set<RentedEdition> rentedEditions;

  @OneToOne(mappedBy = "edition")
  private NewEdition newEdition;

}
