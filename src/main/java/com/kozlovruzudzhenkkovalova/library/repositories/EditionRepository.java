package com.kozlovruzudzhenkkovalova.library.repositories;

import com.kozlovruzudzhenkkovalova.library.entity.Edition;
import com.kozlovruzudzhenkkovalova.library.entity.Publishing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EditionRepository extends JpaRepository<Edition, Long> {

  Page<Edition> findByAuthorsFullNameIn(List<String> fullNames, Pageable pageable);

  Page<Edition> findByNameLike(String name, Pageable pageable);

  Page<Edition> findByGenresNameIn(List<String> genreNames, Pageable pageable);

  Page<Edition> findByPublishingFullNameIn(List<String> fullNames, Pageable pageable);

  Page<Edition> findByYear(String year, Pageable pageable);

  Page<Edition> findByEditionTypeNameIn(List<String> fullNames, Pageable pageable);

  Optional<Edition> findByName(String name);

}

