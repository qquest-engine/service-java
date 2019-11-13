package ua.ithillel.evo.questengine.service.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ithillel.evo.questengine.data.dao.GameDAO;
import ua.ithillel.evo.questengine.data.dao.ProgressDAO;
import ua.ithillel.evo.questengine.data.entity.Game;
import ua.ithillel.evo.questengine.data.entity.Progress;
import ua.ithillel.evo.questengine.service.ProgressService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProgressServiceImpl implements ProgressService {

    private ProgressDAO progressDAO;
    private GameDAO gameDAO;

    @Autowired
    public ProgressServiceImpl(ProgressDAO progressDAO, GameDAO gameDAO) {
        this.progressDAO = progressDAO;
        this.gameDAO = gameDAO;
    }

    @Override
    public Optional<Progress> getById(Long id) {
        return progressDAO.getById(id);
    }

    @Override
    public List<Progress> getAll() {
        return progressDAO.getAll();
    }

    @Override
    public void saveProgressForGame(Long gameId, Progress progress) {
        Game game = gameDAO.getById(gameId).orElse(null);//todo
        game.getProgresses().add(progress);//Does it really makes sense? saving multiple progresses for 1 game ?
        progress.setGame(game);
        gameDAO.save(game);
    }

    @Override
    public void save(Progress progress) {
        progressDAO.save(progress);
    }

    @Override
    public void deleteById(Long id) {
        progressDAO.deleteById(id);
    }
}
