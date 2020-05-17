package com.kozlovruzudzhenkkovalova.library.serviceTests;

import com.kozlovruzudzhenkkovalova.library.repositories.GenreRepository;
import com.kozlovruzudzhenkkovalova.library.service.GenreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GenreServiceTest {

  @Mock
  private GenreRepository genreRepository;
  @InjectMocks
  private GenreService genreService;

  @Test
  public void shouldCallFindAll1Time() {
    genreService.findAllGenres();
    verify(genreRepository, times(1)).findAll();
  }
}