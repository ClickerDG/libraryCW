package com.kozlovruzudzhenkkovalova.library.rest;

import com.kozlovruzudzhenkkovalova.library.entity.Author;
import com.kozlovruzudzhenkkovalova.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
@Slf4j
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public Page<Author> getAllAuthors(@PageableDefault Pageable pageable){
        return authorService.findAllAuthors(pageable); }

}
