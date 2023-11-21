package com.example.universityservice.service;

import com.example.universityservice.entity.Discipline;
import com.example.universityservice.repository.DisciplineRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplineService {


    private final DisciplineRepository disciplineRepository;

    public DisciplineService(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    public ResponseEntity<List<Discipline>> getAllDiscipline(){

        return new ResponseEntity<>((List<Discipline>) disciplineRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Discipline> addDiscipline(Discipline discipline) {

        disciplineRepository.save(discipline);
        return
                new ResponseEntity<>(discipline,HttpStatus.OK);

    }
}
