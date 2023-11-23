package com.example.universityservice.service;

import com.example.universityservice.entity.Discipline;
import com.example.universityservice.exception.DisciplineNotFoundException;
import com.example.universityservice.repository.DisciplineRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<Discipline> getDisciplineById(Long id) throws DisciplineNotFoundException {

        Optional<Discipline> opt_discipline = disciplineRepository.findById(id);
        if(opt_discipline.isPresent()){
            return
                    new ResponseEntity<>(opt_discipline.get(),HttpStatus.OK);
        }
        else
            throw new DisciplineNotFoundException("discipline with id '"+id+"' not found");

    }

    public ResponseEntity<Discipline> updateDiscipline(Long id,Discipline discipline) throws DisciplineNotFoundException {
        Optional<Discipline> opt_discipline = disciplineRepository.findById(id);
        if(opt_discipline.isPresent()){
            opt_discipline.get().setName(discipline.getName());
            opt_discipline.get().setCourse(discipline.getCourse());
            opt_discipline.get().setEndDate(discipline.getEndDate());
            opt_discipline.get().setStartDate(discipline.getStartDate());
            opt_discipline.get().setSpeciality(discipline.getSpeciality());
            opt_discipline.get().setTeacherName(discipline.getName());
            opt_discipline.get().setTotalLabHours(discipline.getTotalLabHours());
            opt_discipline.get().setTotalPracticalHours(discipline.getTotalPracticalHours());
            opt_discipline.get().setTotalLectureHours(discipline.getTotalLectureHours());
            disciplineRepository.save(opt_discipline.get());
            return
                    new ResponseEntity<>(opt_discipline.get(),HttpStatus.OK);

        }
        else
            throw new DisciplineNotFoundException("discipline with id '"+id+"' not found");
    }
}
