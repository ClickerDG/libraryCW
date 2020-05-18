package com.kozlovruzudzhenkkovalova.library.serviceTests;

import com.kozlovruzudzhenkkovalova.library.dto.EditionDto;
import com.kozlovruzudzhenkkovalova.library.entity.Edition;
import com.kozlovruzudzhenkkovalova.library.repositories.AuthorRepository;
import com.kozlovruzudzhenkkovalova.library.repositories.EditionRepository;
import com.kozlovruzudzhenkkovalova.library.repositories.EditionTypeRepository;
import com.kozlovruzudzhenkkovalova.library.repositories.GenreRepository;
import com.kozlovruzudzhenkkovalova.library.repositories.NewEditionRepository;
import com.kozlovruzudzhenkkovalova.library.repositories.PublishingRepository;
import com.kozlovruzudzhenkkovalova.library.service.AuthorService;
import com.kozlovruzudzhenkkovalova.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

  @Mock
  private EditionRepository editionRepository;
  @Mock
  private NewEditionRepository newEditionRepository;
  @Mock
  private GenreRepository genreRepository;
  @Mock
  private PublishingRepository publishingRepository;
  @Mock
  private EditionTypeRepository editionTypeRepository;
  @Mock
  private AuthorRepository authorRepository;
  @Mock
  private AuthorService authorService;
  @InjectMocks
  private BookService bookService;

  @Test
  public void shouldCallFindAll1Time(){
    bookService.searchAllBooks(Pageable.unpaged());
    verify(editionRepository, times(1)).findAll(Pageable.unpaged());
  }

  @Test
  public void shouldThrowExceptionCauseOfBookIsAlreadyExist() {
    when(editionRepository.findByName("book1")).thenReturn(Optional.of(Edition.builder().id("1").build()));
    assertThrows(IllegalArgumentException.class,() -> bookService.addBook(EditionDto.builder()
        .authorNames(Set.of("author1"))
        .name("book1")
        .publishingName("Hello")
        .genreNames(Set.of("horror", "drama"))
        .editionType("Journal")
        .build()));
    verify(authorRepository, times(2)).findByFullNameIn(Set.of("author1"));
    verify(publishingRepository, times(1)).findByFullName("Hello");
    verify(genreRepository, times(1)).findByNameIn(Set.of("horror", "drama"));
    verify(editionTypeRepository, times(1)).findByName("Journal");
    verify(editionRepository, times(1)).findByName("book1");
  }
}
