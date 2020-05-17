package com.kozlovruzudzhenkkovalova.library.serviceTests;

import com.kozlovruzudzhenkkovalova.library.repositories.PublishingRepository;
import com.kozlovruzudzhenkkovalova.library.service.PublishingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PublishingServiceTest {

  @Mock
  private PublishingRepository publishingRepository;
  @InjectMocks
  private PublishingService publishingService;

  @Test
  public void shouldCallFindAll1Time() {
    publishingService.findAllPublishings();
    verify(publishingRepository, times(1)).findAll();
  }

}
