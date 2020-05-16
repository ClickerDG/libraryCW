package com.kozlovruzudzhenkkovalova.library.rest;

import com.kozlovruzudzhenkkovalova.library.entity.EditionType;
import com.kozlovruzudzhenkkovalova.library.service.EditionTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/editionType")
@Slf4j
public class EditionTypeController {
    private final EditionTypeService edTypeService;

    @GetMapping
    public List<EditionType> getAllTypes() {
        return edTypeService.findAllTypes();
    }
}
