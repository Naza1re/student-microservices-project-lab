package com.example.universityservice.service;

import com.example.universityservice.entity.Discipline;
import com.example.universityservice.entity.Title;
import com.example.universityservice.exception.DisciplineNotFoundException;
import com.example.universityservice.exception.TitleNotFoundException;
import com.example.universityservice.repository.DisciplineRepository;
import com.example.universityservice.repository.TitleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TitleService {


    private final TitleRepository titleRepository;
    private final DisciplineRepository disciplineRepository;


    public TitleService(TitleRepository titleRepository, DisciplineRepository disciplineRepository) {
        this.titleRepository = titleRepository;
        this.disciplineRepository = disciplineRepository;
    }


    public ResponseEntity<List<Title>> getAllTitlesByIdOfDiscipline(Long id) throws DisciplineNotFoundException {
        Optional<Discipline> opt_discipline =  disciplineRepository.findById(id);
        if (opt_discipline.isPresent()) {
            return
                    new ResponseEntity<>(opt_discipline.get().getTitles(),HttpStatus.OK);
        }
        else
            throw new DisciplineNotFoundException("discipline with id '"+ id+"' not found");
    }

    public ResponseEntity<Title> addTitleToDiscipline(Long id, Title title) throws DisciplineNotFoundException {
        Optional<Discipline> opt_discipline =  disciplineRepository.findById(id);
        if (opt_discipline.isPresent()) {
            title.setDiscipline(opt_discipline.get());
            titleRepository.save(title);
            return
                    new ResponseEntity<>(title,HttpStatus.OK);
        }
        else throw new DisciplineNotFoundException("discipline with id '" +id+ "' not found exception");

    }

    public ResponseEntity<Title> updateTitle(Long id, Title title) throws TitleNotFoundException {

        Optional<Title> opt_title = titleRepository.findById(id);
        if(opt_title.isPresent()){
            opt_title.get().setName(title.getName());
            opt_title.get().setLabHours(title.getLabHours());
            opt_title.get().setLectureHours(title.getLabHours());
            opt_title.get().setPracticalHours(title.getPracticalHours());
            titleRepository.save(opt_title.get());
            return
                    new ResponseEntity<>(opt_title.get(),HttpStatus.OK);
        }
        else
            throw new TitleNotFoundException("title with id '"+id+"' not found");
    }

    public HttpStatus deleteTitleById(Long id) throws TitleNotFoundException {

        Optional<Title> opt_title = titleRepository.findById(id);

        if(opt_title.isPresent()){
            titleRepository.delete(opt_title.get());
            return HttpStatus.OK;
        }
        else
            throw new TitleNotFoundException("title with id '"+id+"' not found");

    }
}
