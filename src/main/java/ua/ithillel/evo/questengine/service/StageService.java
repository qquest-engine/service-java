package ua.ithillel.evo.questengine.service;

import ua.ithillel.evo.questengine.data.entity.Stage;

import java.util.Optional;

public interface StageService {

    Optional<Stage> getById(Long id);

    Iterable<Stage> getAll();

    void save(Stage stage);

    void delete(Stage stage);
}
