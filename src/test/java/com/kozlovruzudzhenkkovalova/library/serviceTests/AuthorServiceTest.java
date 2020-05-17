package com.kozlovruzudzhenkkovalova.library.serviceTests;

import com.kozlovruzudzhenkkovalova.library.dto.AuthorDto;
import com.kozlovruzudzhenkkovalova.library.entity.Author;
import com.kozlovruzudzhenkkovalova.library.repositories.AuthorRepository;
import com.kozlovruzudzhenkkovalova.library.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

  @Mock
  private AuthorRepository authorRepository;

  @InjectMocks
  private AuthorService authorService;

  @Test
  public void shouldThrowException() {
    var authorDto = AuthorDto.builder().fullName("Username").build();
    var author = Author.builder().id(1L).fullName("Username").build();
    Optional<Author> authorOptional = Optional.ofNullable(author);
    when(authorRepository.findByFullName("Username")).thenReturn(authorOptional);
    assertThrows(IllegalArgumentException.class, () -> authorService.addAuthor(authorDto));
  }

  @Test
  public void shouldCallAuthorRepositoryFindAllMethod1Time() {
    authorService.findAllAuthors();
    verify(authorRepository, times(1)).findAll();

  }

}
