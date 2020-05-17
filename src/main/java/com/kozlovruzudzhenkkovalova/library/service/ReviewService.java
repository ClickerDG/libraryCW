package com.kozlovruzudzhenkkovalova.library.service;

import com.kozlovruzudzhenkkovalova.library.dto.ReviewDto;
import com.kozlovruzudzhenkkovalova.library.entity.Edition;
import com.kozlovruzudzhenkkovalova.library.entity.Review;
import com.kozlovruzudzhenkkovalova.library.entity.User;
import com.kozlovruzudzhenkkovalova.library.repositories.EditionRepository;
import com.kozlovruzudzhenkkovalova.library.repositories.ReviewRepository;
import com.kozlovruzudzhenkkovalova.library.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

  private final ReviewRepository reviewRepository;
  private final EditionRepository editionRepository;
  private final UserRepository userRepository;

  public List<Review> findAllReviewForBook(String bookName) {
    return reviewRepository.findAllByEditionName(bookName);
  }

  public void addReviewForBook(ReviewDto reviewDto) {
    var edition = editionRepository.findByName(reviewDto.getBookName())
        .orElseThrow(() -> new UsernameNotFoundException("No such book"));
    var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    var username = ((UserDetails) principal).getUsername();
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("No such user"));

    reviewRepository.save(Review.builder().edition(edition).user(user).review(reviewDto.getReview()).build());
  }

}
