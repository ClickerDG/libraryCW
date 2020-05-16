package com.kozlovruzudzhenkkovalova.library.repositories;

import com.kozlovruzudzhenkkovalova.library.entity.Edition;
import com.kozlovruzudzhenkkovalova.library.entity.Publishing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EditionRepository extends JpaRepository<Edition, Long> {

  List<Edition> findByAuthorsFullNameIn(List<String> fullNames);

  List<Edition> findByNameLike(String name);

  List<Edition> findByGenresNameIn(List<String> genreNames);

  List<Edition> findByPublishingFullNameIn(List<String> fullNames);

  List<Edition> findByYear(String year);

  List<Edition> findByEditionTypeNameIn(List<String> fullNames);

  Optional<Edition> findByName(String name);

}

