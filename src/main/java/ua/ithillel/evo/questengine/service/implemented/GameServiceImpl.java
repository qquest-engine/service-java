package ua.ithillel.evo.questengine.service.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ithillel.evo.questengine.data.dao.GameDAO;
import ua.ithillel.evo.questengine.data.dao.UserDAO;
import ua.ithillel.evo.questengine.data.entity.Game;
import ua.ithillel.evo.questengine.data.entity.User;
import ua.ithillel.evo.questengine.service.GameService;

import java.util.List;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    private final GameDAO gameDAO;
    private final UserDAO userDAO;

    @Autowired
    public GameServiceImpl(GameDAO gameDAO, UserDAO userDAO) {
        this.gameDAO = gameDAO;
        this.userDAO = userDAO;
    }

    @Override
    public Game getById(Long id) {
        return gameDAO.getById(id);
    }

    @Override
    public List<Game> getGamesByUserId(Long userId) {
        return gameDAO.getGamesByUserId(userId);
    }

    @Override
    public List<Game> getAll() {
        return gameDAO.getAll();
    }

    @Override
    public void createGameForUser(Long userId, Game game) {
        User user = userDAO.getById(userId);
        user.getGames().add(game);
        game.setUser(user);
        userDAO.save(user);
    }

    @Override
    public void save(Game game) {
        gameDAO.save(game);
    }

    @Override
    public void deleteById(Long id) {
        gameDAO.deleteById(id);
    }
}
