package com.kozlovruzudzhenkkovalova.library.serviceTests;

import com.kozlovruzudzhenkkovalova.library.repositories.EditionRepository;
import com.kozlovruzudzhenkkovalova.library.repositories.ReviewRepository;
import com.kozlovruzudzhenkkovalova.library.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

  @Mock
  private ReviewRepository reviewRepository;
  @Mock
  private EditionRepository editionRepository;
  @InjectMocks
  private ReviewService reviewService;

  @Test
  public void shouldCallFindByEditionName1Time() {
    reviewService.findAllReviewForBook("book1");
    verify(reviewRepository,times(1)).findAllByEditionName("book1");
  }

}
