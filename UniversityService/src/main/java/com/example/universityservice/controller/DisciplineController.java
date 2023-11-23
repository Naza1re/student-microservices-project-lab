package com.example.universityservice.controller;

import com.example.universityservice.entity.Discipline;
import com.example.universityservice.exception.DisciplineNotFoundException;
import com.example.universityservice.service.DisciplineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discipline")
public class DisciplineController {

    private final DisciplineService disciplineService;

    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Discipline>> getAllDiscipline(){
        return disciplineService.getAllDiscipline();
    }

    @PostMapping("/add")
    public ResponseEntity<Discipline> addDiscipline(@RequestBody Discipline discipline){
        return disciplineService.addDiscipline(discipline);
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<Discipline> updateDisciplineById(@PathVariable Long id,@RequestBody Discipline discipline) throws DisciplineNotFoundException {
        return disciplineService.updateDiscipline(id,discipline);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Discipline> getDisciplineById(@PathVariable Long id) throws DisciplineNotFoundException {
        return disciplineService.getDisciplineById(id);
    }
}
