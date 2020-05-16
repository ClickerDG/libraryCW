package com.kozlovruzudzhenkkovalova.library.service;

import com.kozlovruzudzhenkkovalova.library.entity.EditionType;
import com.kozlovruzudzhenkkovalova.library.repositories.EditionTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EditionTypeService {
    private final EditionTypeRepository edTypeRepository;
    public List<EditionType> findAllTypes() { return edTypeRepository.findAll();}
}
