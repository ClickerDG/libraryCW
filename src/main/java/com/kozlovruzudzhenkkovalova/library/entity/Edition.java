package com.kozlovruzudzhenkkovalova.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Edition implements Serializable {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "isbn")
  private String id;

  @Column(name = "edition_name", nullable = false)
  private String name;

  @Column(name = "edition_year", length = 10)
  @Size(max = 10)
  private String year;

  @Column(name = "image_url")
  private String imageUrl;

  @JsonIgnoreProperties("editions")
  @ManyToOne
  @JoinColumn(name = "type_id", nullable = false)
  private EditionType editionType;

  @JsonIgnoreProperties("editions")
  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(
      name = "genre_edition",
      joinColumns = {@JoinColumn(name = "isbn")},
      inverseJoinColumns = {@JoinColumn(name = "genre_id")}
  )
  private Set<Genre> genres;

  @JsonIgnoreProperties("editions")
  @ManyToOne
  @JoinColumn(name = "publish_id", nullable = false)
  private Publishing publishing;

  @JsonIgnoreProperties("editions")
  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(
      name = "author_edition",
      joinColumns = {@JoinColumn(name = "isbn")},
      inverseJoinColumns = {@JoinColumn(name = "author_id")}
  )
  private Set<Author> authors;

  @JsonIgnoreProperties("edition")
  @OneToMany(mappedBy = "edition", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
  private Set<Review> reviews;

  @JsonIgnoreProperties("edition")
  @OneToMany(mappedBy = "edition", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
  private Set<RentedEdition> rentedEditions;

  @JsonIgnoreProperties("edition")
  @OneToOne(mappedBy = "edition")
  private NewEdition newEdition;

  @Lob
  @Column(name = "edition_description")
  private String description;

}
