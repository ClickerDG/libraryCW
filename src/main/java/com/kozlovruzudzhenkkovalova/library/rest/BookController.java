package com.kozlovruzudzhenkkovalova.library.rest;

import com.kozlovruzudzhenkkovalova.library.entity.Edition;
import com.kozlovruzudzhenkkovalova.library.entity.Genre;
import com.kozlovruzudzhenkkovalova.library.repositories.EditionRepository;
import com.kozlovruzudzhenkkovalova.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

  private final BookService bookService;

  @GetMapping
  public ResponseEntity<?> getAll() {
    return ResponseEntity.ok(bookService.searchAllBooks());
  }

  @GetMapping("/allNew")
  public ResponseEntity<?> getAllNewEditions() {
    return ResponseEntity.ok(bookService.searchAllNewBooks());
  }

 /* @PostMapping("/byAuthors")
  public List<Edition> searchByAuthors(@RequestBody List<String> authorsNames) {

  }

  @PostMapping("/byName")
  public List<Edition> searchByName(@RequestBody String name) {

  }

  @PostMapping("/byGenres")
  public List<Edition> searchByGenres(@RequestBody List<String> genres) {

  }

  @PostMapping("/byPublishing")
  public List<Edition> searchByPublishing(@RequestBody List<String> publishingName) {

  }

  @PostMapping("/byYear")
  public List<Edition> searchByYear(@RequestBody String year) {

  }

  @PostMapping("/byEditionType")
  public List<Edition> searchByEditionTypes(@RequestBody List<String> editionTypeNames) {

  }*/

}
