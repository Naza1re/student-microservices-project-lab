package com.example.universityservice.controller;

import com.example.universityservice.entity.Title;
import com.example.universityservice.exception.DisciplineNotFoundException;
import com.example.universityservice.exception.TitleNotFoundException;
import com.example.universityservice.service.TitleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{id}/titles")
public class TitleController {


    private final TitleService titleService;

    public TitleController(TitleService titleService) {
        this.titleService = titleService;
    }

    @GetMapping("/all-titles")
    public ResponseEntity<List<Title>> getAllTitlesOfDisciplineById(@PathVariable Long id) throws DisciplineNotFoundException {
        return titleService.getAllTitlesByIdOfDiscipline(id);
    }
    @PostMapping("/add-title")
    public ResponseEntity<Title> addTitleToDiscipline(@PathVariable Long id,@RequestBody Title title) throws DisciplineNotFoundException {
        return titleService.addTitleToDiscipline(id,title);
    }
    @PutMapping("/{title_id}/update")
    public ResponseEntity<Title> updateTileOfDiscipline(@RequestBody Title title, @PathVariable Long title_id) throws TitleNotFoundException {
        return titleService.updateTitle(title_id,title);
    }
    @DeleteMapping("/{title_id}/delete")
    public HttpStatus deleteTitleById(@PathVariable Long title_id, @PathVariable String id) throws TitleNotFoundException {
        return titleService.deleteTitleById(title_id);
    }

}
