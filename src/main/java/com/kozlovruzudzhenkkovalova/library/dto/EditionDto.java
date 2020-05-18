package com.kozlovruzudzhenkkovalova.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditionDto {

  private String name;

  private String year;

  private String imageUrl;

  private String editionType;

  private Set<String> genreNames;

  private String publishingName;

  private Set<String> authorNames;

  private String description;
}
