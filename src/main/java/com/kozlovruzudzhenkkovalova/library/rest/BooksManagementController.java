package com.kozlovruzudzhenkkovalova.library.rest;

import com.kozlovruzudzhenkkovalova.library.dto.EditionDto;
import com.kozlovruzudzhenkkovalova.library.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/bookManagement")
@RequiredArgsConstructor
@Slf4j
public class BooksManagementController {

  private final BookService bookService;

  @PostMapping("/delete")
  public ResponseEntity<?> deleteBook(@RequestBody EditionDto editionDto) {
    try {
      bookService.deleteBook(editionDto);
    }
    catch (UsernameNotFoundException e) {
      log.error(e.getMessage());
      return ResponseEntity.badRequest().body("No such book");
    }
    return ResponseEntity.ok().body("Book was successfully deleted");
  }

  @PostMapping("/add")
  public ResponseEntity<?> addNewBook(@RequestBody EditionDto editionDto) {
    try {
      bookService.addBook(editionDto);
    }
    catch (IllegalArgumentException e) {
      log.error(e.getMessage());
      return ResponseEntity.badRequest().body("Book with such username is already exist");
    }
    return ResponseEntity.ok("Book was successfully added");
  }

}
