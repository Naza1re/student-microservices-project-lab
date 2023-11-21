package com.example.universityservice.repository;

import com.example.universityservice.entity.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends CrudRepository<Title,Long> {
}
