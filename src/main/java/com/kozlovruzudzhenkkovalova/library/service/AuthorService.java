package com.kozlovruzudzhenkkovalova.library.service;

import com.kozlovruzudzhenkkovalova.library.dto.AuthorDto;
import com.kozlovruzudzhenkkovalova.library.entity.Author;
import com.kozlovruzudzhenkkovalova.library.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Page<Author> findAllAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    public Author addAuthor(AuthorDto authorDto) {

        var author = Author.builder()
                .fullName(authorDto.getFullName())
                .build();
        return addAuthor(author);
    }

    private Author addAuthor(Author author) {
        authorRepository.findByFullName(author.getFullName()).ifPresent(thisAuthor -> {
            throw new IllegalArgumentException(
                    "Such author is already exist. Full name: " + thisAuthor.getFullName());
        });
        authorRepository.save(author);
        return author;
    }

    public void deleteAuthor(AuthorDto author) {
        var foundedAuthor = authorRepository.findByFullName(author.getFullName())
                .orElseThrow(() -> new UsernameNotFoundException("No such author"));
            authorRepository.delete(foundedAuthor);
    }

}
