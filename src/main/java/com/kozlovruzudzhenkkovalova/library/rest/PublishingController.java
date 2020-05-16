package com.kozlovruzudzhenkkovalova.library.rest;

import com.kozlovruzudzhenkkovalova.library.entity.Publishing;
import com.kozlovruzudzhenkkovalova.library.service.PublishingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/publishing")
@Slf4j
public class PublishingController {
    private final PublishingService publishingService;

    @GetMapping
    public List<Publishing> getAllPublishings() {
        return publishingService.findAllPublishings();
    }
}
