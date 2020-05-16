package com.kozlovruzudzhenkkovalova.library.repositories;

import com.kozlovruzudzhenkkovalova.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

  Set<Author> findByFullNameIn(Set<String> names);

}
