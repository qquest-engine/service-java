package ua.ithillel.evo.questengine.service;

import ua.ithillel.evo.questengine.data.entity.Game;

import java.util.List;

public interface GameService {

    Game getById(Long id);

    List<Game> getGamesByUserId(Long userId);

    List<Game> getAll();

    void createGameForUser(Long userId, Game game);

    void save(Game game);

    void deleteById(Long id);

}
