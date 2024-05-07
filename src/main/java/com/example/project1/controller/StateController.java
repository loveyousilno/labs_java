package com.example.project1.controller;

import com.example.project1.models.State;
import com.example.project1.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;



@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateService stateService;

    // Создание нового штата
    @PostMapping
    public ResponseEntity<State> createState(@RequestBody State state) {
        State newState = stateService.createState(state);
        return new ResponseEntity<>(newState, HttpStatus.CREATED);
    }

    // Получение списка всех штатов
    @GetMapping
    public ResponseEntity<List<State>> getAllStates() {
        List<State> states = stateService.getAllStates();
        return new ResponseEntity<>(states, HttpStatus.OK);
    }

    // Получение штата по ID
    @GetMapping("/{id}")
    public ResponseEntity<State> getStateById(@PathVariable Long id) {
        return stateService.getStateById(id)
                .map(state -> new ResponseEntity<>(state, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Обновление информации о штате
    @PutMapping("/{id}")
    public ResponseEntity<State> updateState(@PathVariable Long id, @RequestBody State newState) {
        State updatedState = stateService.updateState(id, newState);
        return new ResponseEntity<>(updatedState, HttpStatus.OK);
    }

    // Удаление штата
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteState(@PathVariable Long id) {
        stateService.deleteState(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/abbreviation")
    public ResponseEntity<List<State>> getStatesByAbbreviation(@RequestParam String abbreviation) {
        List<State> states = stateService.getStatesByAbbreviation(abbreviation);
        return new ResponseEntity<>(states, HttpStatus.OK);
    }
}
