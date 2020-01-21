package ua.ithillel.evo.questengine.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.ithillel.evo.questengine.data.entity.Game;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    List<Game> getGamesByUserId(Long userId);
}
