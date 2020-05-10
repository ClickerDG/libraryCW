package com.kozlovruzudzhenkkovalova.library.repositories;

import com.kozlovruzudzhenkkovalova.library.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditionTypeRepository extends JpaRepository<EditionType, Long> {
}
