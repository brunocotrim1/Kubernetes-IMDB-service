package com.computacao.nuvem.tittlesmicroservice.service;

import com.computacao.nuvem.tittlesmicroservice.model.Aka;
import com.computacao.nuvem.tittlesmicroservice.model.Ratings;
import com.computacao.nuvem.tittlesmicroservice.model.Title;
import com.computacao.nuvem.tittlesmicroservice.model.repository.AkaRepository;
import com.computacao.nuvem.tittlesmicroservice.model.repository.RatingsRepository;
import com.computacao.nuvem.tittlesmicroservice.model.repository.TitleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class TitleServiceTest {

    @Mock
    private RatingsRepository ratingsRepository;

    @Mock
    private TitleRepository titleRepository;

    @Mock
    private AkaRepository akaRepository;

    @InjectMocks
    private TitleService titleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testTop100MoviesByYear404() {
        List<Ratings> ratingsList = new ArrayList<>();
        when(ratingsRepository.getTop100ByYear("2023", PageRequest.of(0, 100))).thenReturn(Optional.of(ratingsList));

        ResponseEntity<?> response = titleService.top100MoviesByYear(2023);

        verify(ratingsRepository, times(1)).getTop100ByYear("2023", PageRequest.of(0, 100));
        Assertions.assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    public void testListByGenre404() {
        List<Title> titleList = new ArrayList<>();
        when(titleRepository.getMoviesOrSeriesByGenres("Action", PageRequest.of(0, 100))).thenReturn(Optional.of(titleList));

        ResponseEntity<?> response = titleService.listByGenre("Action", 0);

        verify(titleRepository, times(1)).getMoviesOrSeriesByGenres("Action", PageRequest.of(0, 100));
        Assertions.assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    public void testManageTitles404() {
        Title title = new Title();
        title.setId("test");
        when(titleRepository.existsById("NotFound")).thenReturn(true);
        when(titleRepository.save(title)).thenReturn(title);

        ResponseEntity<?> response = titleService.manageTitles(title);

        verify(titleRepository, times(1)).existsById("test");
        Assertions.assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    public void testTitleCatalog404() {

        ResponseEntity<?> response = titleService.titleCatalog(0);

        verify(titleRepository, times(1)).findAll(PageRequest.of(0, 100));
        Assertions.assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    public void testFindByName404() {
        List<Title> titleList = new ArrayList<>();
        when(titleRepository.findByPrimaryTitle("Title")).thenReturn(Optional.of(titleList));

        ResponseEntity<?> response = titleService.findByName("Title");

        verify(titleRepository, times(1)).findByPrimaryTitle("Title");
        Assertions.assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    public void testFindTitleAkas404() {
        List<Aka> akaList = new ArrayList<>();
        when(akaRepository.getByTitleId("123")).thenReturn(Optional.of(akaList));

        ResponseEntity<?> response = titleService.findTitleAkas("123");

        verify(akaRepository, times(1)).getByTitleId("123");
        Assertions.assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    public void testTop100MoviesByYear() {
        List<Ratings> ratingsList = new ArrayList<>();
        ratingsList.add(new Ratings());
        when(ratingsRepository.getTop100ByYear("2023", PageRequest.of(0, 100))).thenReturn(Optional.of(ratingsList));

        ResponseEntity<?> response = titleService.top100MoviesByYear(2023);

        verify(ratingsRepository, times(1)).getTop100ByYear("2023", PageRequest.of(0, 100));
        Assertions.assertEquals(ResponseEntity.ok(ratingsList), response);
    }

    @Test
    public void testListByGenre() {
        List<Title> titleList = new ArrayList<>();
        titleList.add(new Title());
        when(titleRepository.getMoviesOrSeriesByGenres("Action", PageRequest.of(0, 100))).thenReturn(Optional.of(titleList));

        ResponseEntity<?> response = titleService.listByGenre("Action", 0);

        verify(titleRepository, times(1)).getMoviesOrSeriesByGenres("Action", PageRequest.of(0, 100));
        Assertions.assertEquals(ResponseEntity.ok(titleList), response);
    }

    @Test
    public void testManageTitles() {
        Title title = new Title();
        title.setId("test");
        when(titleRepository.existsById("test")).thenReturn(true);
        when(titleRepository.save(title)).thenReturn(title);

        ResponseEntity<?> response = titleService.manageTitles(title);

        verify(titleRepository, times(1)).existsById("test");
        verify(titleRepository, times(1)).save(title);
        Assertions.assertEquals(ResponseEntity.ok(title), response);
    }

    @Test
    public void testTitleCatalog() {
        Page<Title> titlePage = new PageImpl<>(new ArrayList<>());
        when(titleRepository.findAll(PageRequest.of(0, 100))).thenReturn(titlePage);

        ResponseEntity<?> response = titleService.titleCatalog(0);

        verify(titleRepository, times(1)).findAll(PageRequest.of(0, 100));
        Assertions.assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    public void testFindByName() {
        List<Title> titleList = new ArrayList<>();
        titleList.add(new Title());
        when(titleRepository.findByPrimaryTitle("Title")).thenReturn(Optional.of(titleList));

        ResponseEntity<?> response = titleService.findByName("Title");

        verify(titleRepository, times(1)).findByPrimaryTitle("Title");
        Assertions.assertEquals(ResponseEntity.ok(titleList), response);
    }

    @Test
    public void testFindTitleAkas() {
        List<Aka> akaList = new ArrayList<>();
        akaList.add(new Aka());
        when(akaRepository.getByTitleId("123")).thenReturn(Optional.of(akaList));

        ResponseEntity<?> response = titleService.findTitleAkas("123");

        verify(akaRepository, times(1)).getByTitleId("123");
        Assertions.assertEquals(ResponseEntity.ok(akaList), response);
    }
}
