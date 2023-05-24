package com.computacao.nuvem.tittlesmicroservice.controller;

import com.computacao.nuvem.tittlesmicroservice.model.Title;
import com.computacao.nuvem.tittlesmicroservice.service.LogService;
import com.computacao.nuvem.tittlesmicroservice.service.TitleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

public class TitleControllerTest {

    @Mock
    private TitleService titleService;

    @Mock
    private LogService logService;

    @InjectMocks
    private TitleController titleController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testTopHundredYear() {
        int year = 2021;
        when(titleService.top100MoviesByYear(year)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<?> response = titleController.topHundredYear(year);

        verify(logService, times(1)).info("Search for top Hundred by Year was Made where year = " + year);
        verify(titleService, times(1)).top100MoviesByYear(year);
        Assertions.assertEquals(ResponseEntity.ok().build(), response);
    }

    @Test
    public void testByGenre() {
        int page = 1;
        String genre = "Comedy";
        when(titleService.listByGenre(genre, page)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<?> response = titleController.bygenre(page, genre);

        verify(logService, times(1)).info("Search for genre was Made where genre = " + genre + " and page = " + page);
        verify(titleService, times(1)).listByGenre(genre, page);
        Assertions.assertEquals(ResponseEntity.ok().build(), response);
    }

    @Test
    public void testUpdateTitle() {
        Title title = new Title();
        when(titleService.manageTitles(title)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<?> response = titleController.updateTitle(title);

        verify(logService, times(1)).info("Updating Title with value: " + title.toString());
        verify(titleService, times(1)).manageTitles(title);
        Assertions.assertEquals(ResponseEntity.ok().build(), response);
    }

    @Test
    public void testGetTitles() {
        int page = 1;
        when(titleService.titleCatalog(page)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<?> response = titleController.getTitles(page);

        verify(logService, times(1)).info("Search for titles was made for page = " + page);
        verify(titleService, times(1)).titleCatalog(page);
        Assertions.assertEquals(ResponseEntity.ok().build(), response);
    }

    @Test
    public void testFindByName() {
        String name = "Movie Title";
        when(titleService.findByName(name)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<?> response = titleController.getTitles(name);

        verify(logService, times(1)).info("Search by name was made with name= " + name);
        verify(titleService, times(1)).findByName(name);
        Assertions.assertEquals(ResponseEntity.ok().build(), response);
    }

    @Test
    public void testFindTitleAkas() {
        String titleId = "123";
        when(titleService.findTitleAkas(titleId)).thenReturn(ResponseEntity.ok().build());

        ResponseEntity<?> response = titleController.findTitleAkas(titleId);

        verify(logService, times(1)).info("Search for Akas of title with id= " + titleId);
        verify(titleService, times(1)).findTitleAkas(titleId);
        Assertions.assertEquals(ResponseEntity.ok().build(), response);
    }
}
