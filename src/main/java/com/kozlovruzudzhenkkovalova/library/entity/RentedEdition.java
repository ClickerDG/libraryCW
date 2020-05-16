package com.kozlovruzudzhenkkovalova.library.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
public class RentedEdition implements Serializable {
  @Id
  @GeneratedValue
  @Column(name = "rent_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "isbn", nullable = false)
  private Edition edition;

  @ManyToOne
  @JoinColumn(name = "reader_id", nullable = false)
  private User user;

  @Column(name = "date_get")
  private Date dateGet;

  @Column(name = "date_return")
  private Date dateReturn;

}
