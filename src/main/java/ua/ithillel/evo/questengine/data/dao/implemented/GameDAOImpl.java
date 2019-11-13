package ua.ithillel.evo.questengine.data.dao.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.ithillel.evo.questengine.data.dao.GameDAO;
import ua.ithillel.evo.questengine.data.entity.Game;
import ua.ithillel.evo.questengine.data.repository.GameRepository;

import java.util.List;
import java.util.Optional;

@Component
public class GameDAOImpl implements GameDAO {

    private GameRepository gameRepository;

    @Autowired
    public GameDAOImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Optional<Game> getById(Long id) {
        return gameRepository.findById(id);
    }

    @Override
    public List<Game> getAll() {
        return (List<Game>) gameRepository.findAll();
    }

    @Override
    public void save(Game game) {
        gameRepository.save(game);
    }

    @Override
    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }
}
