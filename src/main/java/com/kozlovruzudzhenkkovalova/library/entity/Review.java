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
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review implements Serializable {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  @Column(name = "review_id", nullable = false)
  private Long id;

  @JsonIgnoreProperties("reviews")
  @ManyToOne
  @JoinColumn(name = "isbn", nullable = false)
  private Edition edition;

  @JsonIgnoreProperties("reviews")
  @ManyToOne
  @JoinColumn(name = "reader_id", nullable = false)
  private User user;

  @JsonIgnoreProperties("reviews")
  @Column(name = "review_content", length = 500)
  @Size(max = 500)
  private String review;

}
