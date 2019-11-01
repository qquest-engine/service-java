package ua.ithillel.evo.questengine.service.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ithillel.evo.questengine.data.dao.StageDAO;
import ua.ithillel.evo.questengine.data.entity.Stage;
import ua.ithillel.evo.questengine.service.StageService;

import java.util.Optional;

@Service
public class StageServiceImpl implements StageService {

    private StageDAO stageDAO;

    @Autowired
    public StageServiceImpl(StageDAO stageDAO) {
        this.stageDAO = stageDAO;
    }

    @Override
    public void save(Stage stage) {
        stageDAO.save(stage);
    }

    @Override
    public Optional<Stage> getById(Long id) {
        return stageDAO.getById(id);
    }

    @Override
    public Iterable<Stage> getAll() {
        return stageDAO.getAll();
    }

    @Override
    public void delete(Stage stage) {
        stageDAO.delete(stage);
    }
}
