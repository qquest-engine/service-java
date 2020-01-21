package ua.ithillel.evo.questengine.data.dao.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.ithillel.evo.questengine.data.dao.GameDAO;
import ua.ithillel.evo.questengine.data.entity.Game;
import ua.ithillel.evo.questengine.data.repository.GameRepository;

import java.util.List;

@Component
public class GameDAOImpl implements GameDAO {

    private GameRepository gameRepository;

    @Autowired
    public GameDAOImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game getById(Long id) {
        return this.gameRepository.findById(id).orElse(null);
    }

    @Override
    public List<Game> getGamesByUserId(Long userId) {
        return this.gameRepository.getGamesByAppUserId(userId);
    }

    @Override
    public List<Game> getAll() {
        return (List<Game>) this.gameRepository.findAll();
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
