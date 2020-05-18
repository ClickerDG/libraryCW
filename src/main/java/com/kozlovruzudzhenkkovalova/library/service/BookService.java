package com.kozlovruzudzhenkkovalova.library.service;

import com.kozlovruzudzhenkkovalova.library.dto.AuthorDto;
import com.kozlovruzudzhenkkovalova.library.dto.EditionDto;
import com.kozlovruzudzhenkkovalova.library.dto.RentedEditionDto;
import com.kozlovruzudzhenkkovalova.library.entity.Author;
import com.kozlovruzudzhenkkovalova.library.entity.Edition;
import com.kozlovruzudzhenkkovalova.library.entity.NewEdition;
import com.kozlovruzudzhenkkovalova.library.entity.RentedEdition;
import com.kozlovruzudzhenkkovalova.library.repositories.AuthorRepository;
import com.kozlovruzudzhenkkovalova.library.repositories.EditionRepository;
import com.kozlovruzudzhenkkovalova.library.repositories.EditionTypeRepository;
import com.kozlovruzudzhenkkovalova.library.repositories.GenreRepository;
import com.kozlovruzudzhenkkovalova.library.repositories.NewEditionRepository;
import com.kozlovruzudzhenkkovalova.library.repositories.PublishingRepository;
import com.kozlovruzudzhenkkovalova.library.repositories.RentedEditionRepository;
import com.kozlovruzudzhenkkovalova.library.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

  private final EditionRepository editionRepository;
  private final NewEditionRepository newEditionRepository;
  private final AuthorRepository authorRepository;
  private final PublishingRepository publishingRepository;
  private final GenreRepository genreRepository;
  private final EditionTypeRepository editionTypeRepository;
  private final RentedEditionRepository rentedEditionRepository;
  private final UserRepository userRepository;
  private final AuthorService authorService;

  public Page<Edition> searchAllBooks(Pageable pageable) {
    return editionRepository.findAll(pageable);
  }

  public List<Edition> searchAllNewBooks(Pageable pageable) {
    return newEditionRepository.findAll(pageable).stream().map(NewEdition::getEdition).collect(Collectors.toList());
  }


  public Page<Edition> searchByAuthors(List<String> authorNames, Pageable pageable) {
    return editionRepository.findByAuthorsFullNameIn(authorNames, pageable);
  }

  public Page<Edition> searchByName(String name, Pageable pageable) {
    return editionRepository.findByNameLike(name, pageable);
  }

  public Page<Edition> searchByGenres(List<String> genres, Pageable pageable) {
    return editionRepository.findByGenresNameIn(genres, pageable);
  }

  public Page<Edition> searchByPublishing(List<String> publishingName, Pageable pageable) {
    return editionRepository.findByPublishingFullNameIn(publishingName, pageable);
  }

  public Page<Edition> searchByYear(String year, Pageable pageable) {
    return editionRepository.findByYear(year, pageable);
  }

  public Page<Edition> searchByEditionTypes(List<String> editionTypeNames, Pageable pageable) {
    return editionRepository.findByEditionTypeNameIn(editionTypeNames, pageable);
  }

  public void addBook(EditionDto editionDto) {
    var authorNames = editionDto.getAuthorNames();
    var authors = authorRepository.findByFullNameIn(authorNames);
    var publishing = publishingRepository.findByFullName(editionDto.getPublishingName());
    var genres = genreRepository.findByNameIn(editionDto.getGenreNames());
    var editionType = editionTypeRepository.findByName(editionDto.getEditionType());
    var listOfNotFoundAuthors = new HashSet<>(authorNames);
    var foundedAuthors = authors.stream().map(Author::getFullName).collect(Collectors.toList());
    listOfNotFoundAuthors.removeAll(foundedAuthors);
    for (String authorName: listOfNotFoundAuthors) {
      authorService.addAuthor(AuthorDto.builder().fullName(authorName).build());
    }
    authors = authorRepository.findByFullNameIn(authorNames);

    var edition = Edition.builder()
        .name(editionDto.getName())
        .authors(authors)
        .year(editionDto.getYear())
        .imageUrl(editionDto.getImageUrl())
        .publishing(publishing)
        .genres(genres)
        .editionType(editionType)
        .description(editionDto.getDescription())
        .build();

    editionRepository.findByName(editionDto.getName()).ifPresent(thisBook -> {
      throw new IllegalArgumentException(
          "Book with such name is already exist. Name: " + thisBook.getName());
    });
    editionRepository.save(edition);
    var thisEdition = editionRepository.findByName(edition.getName()).get();
    newEditionRepository.save(NewEdition.builder().edition(thisEdition).isbn(thisEdition.getId()).build());
  }

  public void deleteBook(EditionDto editionDto) {
    var edition = editionRepository.findByName(editionDto.getName())
        .orElseThrow(() -> new UsernameNotFoundException("No such book"));
    editionRepository.delete(edition);
  }

  public void rentBook(RentedEditionDto rentedEditionDto) {
    var edition = editionRepository.findByName(rentedEditionDto.getBookName())
        .orElseThrow(() -> new UsernameNotFoundException("No such book"));
    var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    var username = ((UserDetails) principal).getUsername();
    var user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("No such user"));
    var rentedEditions = user.getRentedEditions();
    rentedEditions.forEach((rentedEdition -> {
      if(rentedEdition.getDateReturn().before(new Date())) {
        throw new RequestRejectedException("You already delayed some books");
      }
    }));
    rentedEditionRepository.save(RentedEdition.builder()
        .edition(edition)
        .dateGet(rentedEditionDto.getDateGet())
        .dateReturn(rentedEditionDto.getDateReturn())
        .user(user)
        .build());
  }

}
