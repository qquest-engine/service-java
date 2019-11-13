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
import java.util.Optional;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    private GameDAO gameDAO;
    private UserDAO userDAO;

    @Autowired
    public GameServiceImpl(GameDAO gameDAO, UserDAO userDAO) {
        this.gameDAO = gameDAO;
        this.userDAO = userDAO;
    }

    @Override
    public Optional<Game> getById(Long id) {
        return gameDAO.getById(id);
    }

    @Override
    public List<Game> getAll() {
        return gameDAO.getAll();
    }

    @Override
    public void createGameForUser(Long userId, Game game) {
        User user = userDAO.getUserByEmailAndAndPassword("string", "string").orElse(null);//todo - actually user id should be kept in memory after login
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
