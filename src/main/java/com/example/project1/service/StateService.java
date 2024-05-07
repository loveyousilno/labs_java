package com.example.project1.service;

import com.example.project1.models.State;
import com.example.project1.repositories.StateRepository;
import com.example.project1.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private Cache cache;

    // Создание нового штата
    public State createState(State state) {
        return stateRepository.save(state);
    }

    // Получение списка всех штатов
    public List<State> getAllStates() {
        return stateRepository.findAll();
    }

    // Получение штата по ID
    public Optional<State> getStateById(Long id) {
        if (cache.containsState(id)) {
            return Optional.of(cache.getState(id));
        } else {
            Optional<State> stateOptional = stateRepository.findById(id);
            stateOptional.ifPresent(state -> cache.putState(id, state));
            return stateOptional;
        }
    }

    // Обновление информации о штате
    public State updateState(Long id, State newState) {
        // Перед обновлением информации о штате, удалим его из кэша
        cache.removeState(id);
        // Обновляем информацию о штате в базе данных
        return stateRepository.findById(id)
                .map(state -> {
                    state.setState(newState.getState());
                    state.setStateAbbreviation(newState.getStateAbbreviation());
                    // Сохраняем обновленный объект State в базе данных
                    return stateRepository.save(state);
                })
                .orElseGet(() -> {
                    newState.setId(id);
                    // Создаем новый объект State, если не найден существующий по заданному id
                    return stateRepository.save(newState);
                });
    }

    // Получение списка штатов по сокращению
    public List<State> getStatesByAbbreviation(String abbreviation) {
        return stateRepository.findStatesByAbbreviation(abbreviation);
    }

    // Удаление штата
    public void deleteState(Long id) {
        // Перед удалением штата, удалим его из кэша
        cache.removeState(id);
        // Удаляем штат из базы данных
        stateRepository.deleteById(id);
    }
}


