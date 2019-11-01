package ua.ithillel.evo.questengine.data.dao;

import ua.ithillel.evo.questengine.data.entity.Stage;

import java.util.Optional;

public interface StageDAO {

    void save(Stage stage);

    Optional<Stage> getById(Long id);

    Iterable<Stage> getAll();

    void delete(Stage stage);

}
