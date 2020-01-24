package ua.ithillel.evo.questengine.service.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ithillel.evo.questengine.data.dao.GameDAO;
import ua.ithillel.evo.questengine.data.dao.AppUserDAO;
import ua.ithillel.evo.questengine.data.entity.Game;
import ua.ithillel.evo.questengine.data.entity.AppUser;
import ua.ithillel.evo.questengine.service.GameService;

import java.util.List;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    private final GameDAO gameDAO;
    private final AppUserDAO appUserDAO;

    @Autowired
    public GameServiceImpl(GameDAO gameDAO, AppUserDAO appUserDAO) {
        this.gameDAO = gameDAO;
        this.appUserDAO = appUserDAO;
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
        AppUser appUser = appUserDAO.getById(userId);
        appUser.getGames().add(game);
        game.setAppUser(appUser);
        appUserDAO.save(appUser);
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
