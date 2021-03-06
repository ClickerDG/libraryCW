package com.kozlovruzudzhenkkovalova.library.rest;

import com.kozlovruzudzhenkkovalova.library.dto.AuthorDto;
import com.kozlovruzudzhenkkovalova.library.entity.Author;
import com.kozlovruzudzhenkkovalova.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/authorManagement")
@Slf4j
public class AuthorManagementController {

    private final AuthorService authorService;

    @GetMapping
    public Page<Author> getAllAuthors(@PageableDefault Pageable pageable){
        return authorService.findAllAuthors(pageable);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewAuthor(@RequestBody AuthorDto authorDto){
        try {
            authorService.addAuthor(authorDto);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Such author is already exists");
        }
        return ResponseEntity.ok("Author is successfully created");
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteAuthor(@RequestBody AuthorDto authorDto){
        try{
            authorService.deleteAuthor(authorDto);
        }
        catch (UsernameNotFoundException e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("No such author");
        }
        return ResponseEntity.ok("Author was successfully deleted");
    }

}
