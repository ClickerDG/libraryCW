package com.kozlovruzudzhenkkovalova.library.service;

import com.kozlovruzudzhenkkovalova.library.entity.Edition;
import com.kozlovruzudzhenkkovalova.library.entity.NewEdition;
import com.kozlovruzudzhenkkovalova.library.repositories.EditionRepository;
import com.kozlovruzudzhenkkovalova.library.repositories.NewEditionRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BookService {

  private final EditionRepository editionRepository;
  private final NewEditionRepository newEditionRepository;

  public List<Edition> searchAllBooks() {
    return editionRepository.findAll();
  }

  public List<Edition> searchAllNewBooks() {
    return newEditionRepository.findAll().stream().map(NewEdition::getEdition).collect(Collectors.toList());
  }
}
