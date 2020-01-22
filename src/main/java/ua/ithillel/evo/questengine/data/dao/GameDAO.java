package ua.ithillel.evo.questengine.data.dao;

import ua.ithillel.evo.questengine.data.entity.Game;

import java.util.List;

public interface GameDAO {

    Game getById(Long id);

    List<Game> getGamesByUserId(Long userId);

    List<Game> getAll();

    void save(Game game);

    void deleteById(Long id);

}
