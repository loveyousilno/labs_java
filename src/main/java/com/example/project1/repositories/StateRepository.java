package com.example.project1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.project1.models.State;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface StateRepository extends JpaRepository<State, Long> {

    @Query("SELECT s FROM State s WHERE s.stateAbbreviation = :abbreviation")
    List<State> findStatesByAbbreviation(@Param("abbreviation") String abbreviation);
}

//jpql