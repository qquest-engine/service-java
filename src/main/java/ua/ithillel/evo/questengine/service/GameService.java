package ua.ithillel.evo.questengine.service;

import ua.ithillel.evo.questengine.data.entity.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {

    Optional<Game> getById(Long id);

    List<Game> getAll();

    void createGameForUser(Long userId, Game game);

    void save(Game game);

    void deleteById(Long id);

}
