package com.kozlovruzudzhenkkovalova.library.rest;

import com.kozlovruzudzhenkkovalova.library.dto.RentedEditionDto;
import com.kozlovruzudzhenkkovalova.library.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Slf4j
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

  @PostMapping("/byAuthors")
  public ResponseEntity<?> searchByAuthors(@RequestBody List<String> authorsNames) {
    return ResponseEntity.ok(bookService.searchByAuthors(authorsNames));
  }

  @PostMapping("/byName")
  public ResponseEntity<?> searchByName(@RequestBody String name) {
    return ResponseEntity.ok(bookService.searchByName(name));
  }

  @PostMapping("/byGenres")
  public ResponseEntity<?> searchByGenres(@RequestBody List<String> genres) {
    return ResponseEntity.ok(bookService.searchByGenres(genres));
  }

  @PostMapping("/byPublishing")
  public ResponseEntity<?> searchByPublishing(@RequestBody List<String> publishingName) {
    return ResponseEntity.ok(bookService.searchByPublishing(publishingName));
  }

  @PostMapping("/byYear")
  public ResponseEntity<?> searchByYear(@RequestBody String year) {
    return ResponseEntity.ok(bookService.searchByYear(year));
  }

  @PostMapping("/byEditionType")
  public ResponseEntity<?> searchByEditionTypes(@RequestBody List<String> editionTypeNames) {
    return ResponseEntity.ok(bookService.searchByEditionTypes(editionTypeNames));
  }

  @PostMapping("/rent")
  public ResponseEntity<?> rentBook(@RequestBody RentedEditionDto rentedEdition) {
    try {
      bookService.rentBook(rentedEdition);
    }
    catch (RequestRejectedException e) {
      log.error(e.getMessage());
      return ResponseEntity.badRequest().body(e.getMessage());
    }
    return ResponseEntity.ok("Book was successfully rented");
  }

}
