package ua.ithillel.evo.questengine.data.dao.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.ithillel.evo.questengine.data.dao.ProgressDAO;
import ua.ithillel.evo.questengine.data.entity.Progress;
import ua.ithillel.evo.questengine.data.repository.ProgressRepository;
import ua.ithillel.evo.questengine.exception.NotFoundException;

import java.util.List;

@Component
public class ProgressDAOImpl implements ProgressDAO {

    private final ProgressRepository progressRepository;

    @Autowired
    public ProgressDAOImpl(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    @Override
    public Progress getById(Long id) {
        return progressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("There is no Progress with id " + id));
    }

    @Override
    public List<Progress> getByGameId(Long gameId) {
        return progressRepository.getProgressesByGame_IdOrderById(gameId);
    }

    @Override
    public List<Progress> getAll() {
        return progressRepository.findAll();
    }

    @Override
    public void save(Progress progress) {
        progressRepository.save(progress);
    }

    @Override
    public void deleteById(Long id) {
        progressRepository.deleteById(id);
    }
}
