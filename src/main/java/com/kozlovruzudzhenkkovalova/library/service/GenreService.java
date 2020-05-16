package com.kozlovruzudzhenkkovalova.library.service;
import com.kozlovruzudzhenkkovalova.library.entity.Genre;
import com.kozlovruzudzhenkkovalova.library.repositories.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;
    public List<Genre> findAllGenres() { return genreRepository.findAll();}
}
