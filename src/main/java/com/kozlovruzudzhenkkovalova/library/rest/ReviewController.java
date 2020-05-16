package com.kozlovruzudzhenkkovalova.library.rest;

import com.kozlovruzudzhenkkovalova.library.dto.ReviewDto;
import com.kozlovruzudzhenkkovalova.library.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

  private final ReviewService reviewService;

  @GetMapping
  public ResponseEntity<?> getAllForBookName(@RequestParam String bookName) {
    return ResponseEntity.ok(reviewService.findAllReviewForBook(bookName));
  }

  @PostMapping("/add")
  public ResponseEntity<?> addReview(@RequestBody ReviewDto reviewDto) {
    try {
      reviewService.addReviewForBook(reviewDto);
    }
    catch (UsernameNotFoundException e) {
      log.error(e.getMessage());
      return ResponseEntity.badRequest().body("Something is not ok with review json");
    }
    return ResponseEntity.ok("Review was successfully added");
  }

}
