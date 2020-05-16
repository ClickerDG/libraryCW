package com.kozlovruzudzhenkkovalova.library.rest;

import com.kozlovruzudzhenkkovalova.library.entity.Author;
import com.kozlovruzudzhenkkovalova.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
@Slf4j
public class AuthorController {
    private final AuthorService authorService;
    @GetMapping
    public List<Author> getAllAuthors(){ return authorService.findAllAuthors(); }

}
