package com.kozlovruzudzhenkkovalova.library.rest;

import com.kozlovruzudzhenkkovalova.library.dto.RentedEditionDto;
import com.kozlovruzudzhenkkovalova.library.entity.Edition;
import com.kozlovruzudzhenkkovalova.library.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
  public Page<Edition> getAll(@PageableDefault Pageable pageable) {
    return bookService.searchAllBooks(pageable);
  }

  @GetMapping("/allNew")
  public ResponseEntity<?> getAllNewEditions(@PageableDefault Pageable pageable) {
    return ResponseEntity.ok(bookService.searchAllNewBooks(pageable));
  }

  @PostMapping("/byAuthors")
  public Page<Edition> searchByAuthors(@RequestBody List<String> authorsNames, @PageableDefault Pageable pageable) {
    return bookService.searchByAuthors(authorsNames, pageable);
  }

  @PostMapping("/byName")
  public Page<Edition> searchByName(@RequestBody String name, @PageableDefault Pageable pageable) {
    return bookService.searchByName(name, pageable);
  }

  @PostMapping("/byGenres")
  public Page<Edition> searchByGenres(@RequestBody List<String> genres, @PageableDefault Pageable pageable) {
    return bookService.searchByGenres(genres, pageable);
  }

  @PostMapping("/byPublishing")
  public Page<Edition> searchByPublishing(@RequestBody List<String> publishingName, @PageableDefault Pageable pageable) {
    return bookService.searchByPublishing(publishingName, pageable);
  }

  @PostMapping("/byYear")
  public Page<Edition> searchByYear(@RequestBody String year, @PageableDefault Pageable pageable) {
    return bookService.searchByYear(year, pageable);
  }

  @PostMapping("/byEditionType")
  public Page<Edition> searchByEditionTypes(@RequestBody List<String> editionTypeNames, @PageableDefault Pageable pageable) {
    return bookService.searchByEditionTypes(editionTypeNames, pageable);
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
