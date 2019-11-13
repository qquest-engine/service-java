package ua.ithillel.evo.questengine.data.dao.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.ithillel.evo.questengine.data.dao.ProgressDAO;
import ua.ithillel.evo.questengine.data.entity.Progress;
import ua.ithillel.evo.questengine.data.repository.ProgressRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ProgressDAOImpl implements ProgressDAO {

    private ProgressRepository progressRepository;

    @Autowired
    public ProgressDAOImpl(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    @Override
    public Optional<Progress> getById(Long id) {
        return progressRepository.findById(id);
    }

    @Override
    public List<Progress> getAll() {
        return (List<Progress>) progressRepository.findAll();
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
