package com.kozlovruzudzhenkkovalova.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentedEdition implements Serializable {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  @Column(name = "rent_id", nullable = false)
  private Long id;

  @JsonIgnoreProperties("rentedEditions")
  @ManyToOne
  @JoinColumn(name = "isbn", nullable = false)
  private Edition edition;

  @JsonIgnoreProperties("rentedEditions")
  @ManyToOne
  @JoinColumn(name = "reader_id", nullable = false)
  private User user;

  @Column(name = "date_get")
  private Date dateGet;

  @Column(name = "date_return")
  private Date dateReturn;

}
