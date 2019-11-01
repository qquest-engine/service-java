package ua.ithillel.evo.questengine.data.dao.implemented;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.ithillel.evo.questengine.data.dao.StageDAO;
import ua.ithillel.evo.questengine.data.entity.Stage;
import ua.ithillel.evo.questengine.data.repository.StageRepository;

import java.util.Optional;

@Slf4j
@Component
public class StageDAOImpl implements StageDAO {

    private StageRepository stageRepository;

    @Autowired
    public StageDAOImpl(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }


    @Override
    public void save(Stage stage) {
        stageRepository.save(stage);
    }

    @Override
    public Optional<Stage> getById(Long id) {
        return stageRepository.findById(id);
    }

    @Override
    public Iterable<Stage> getAll() {
        return stageRepository.findAll();
    }

    @Override
    public void delete(Stage stage) {
        stageRepository.delete(stage);
    }

}
