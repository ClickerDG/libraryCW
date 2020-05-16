package com.kozlovruzudzhenkkovalova.library.service;

import com.kozlovruzudzhenkkovalova.library.entity.Publishing;
import com.kozlovruzudzhenkkovalova.library.repositories.PublishingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublishingService {
    private final PublishingRepository publishingRepository;
    public List<Publishing> findAllPublishings() { return publishingRepository.findAll();}
}
