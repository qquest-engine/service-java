package ua.ithillel.evo.questengine.data.dao.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.ithillel.evo.questengine.data.dao.GameDAO;
import ua.ithillel.evo.questengine.data.entity.Game;
import ua.ithillel.evo.questengine.data.repository.GameRepository;
import ua.ithillel.evo.questengine.exception.NotFoundException;

import java.util.List;

@Component
public class GameDAOImpl implements GameDAO {

    private final GameRepository gameRepository;

    @Autowired
    public GameDAOImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game getById(Long id) {
        return this.gameRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("There is no Game with id " + id));
    }

    @Override
    public List<Game> getGamesByUserId(Long userId) {
        return this.gameRepository.getGamesByAppUserId(userId);
    }

    @Override
    public List<Game> getAll() {
        return this.gameRepository.findAll();
    }

    @Override
    public void save(Game game) {
        this.gameRepository.save(game);
    }

    @Override
    public void deleteById(Long id) {
        this.gameRepository.deleteById(id);
    }
}
