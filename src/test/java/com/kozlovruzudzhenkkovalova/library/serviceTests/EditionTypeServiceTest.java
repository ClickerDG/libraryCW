package com.kozlovruzudzhenkkovalova.library.serviceTests;

import com.kozlovruzudzhenkkovalova.library.repositories.EditionTypeRepository;
import com.kozlovruzudzhenkkovalova.library.service.EditionTypeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EditionTypeServiceTest {

  @Mock
  private EditionTypeRepository editionTypeRepository;
  @InjectMocks
  private EditionTypeService editionTypeService;

  @Test
  public void shouldCallFindAll1Time() {
    editionTypeService.findAllTypes();
    verify(editionTypeRepository, times(1)).findAll();
  }
}