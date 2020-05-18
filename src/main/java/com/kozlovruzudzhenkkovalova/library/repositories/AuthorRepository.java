package com.kozlovruzudzhenkkovalova.library.repositories;

import com.kozlovruzudzhenkkovalova.library.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

  Page<Author> findAll(Pageable page);

  Optional<Author> findByFullName(String fullname);

  Set<Author> findByFullNameIn(Set<String> names);

}
