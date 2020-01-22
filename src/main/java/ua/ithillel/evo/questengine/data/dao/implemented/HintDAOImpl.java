package ua.ithillel.evo.questengine.data.dao.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.ithillel.evo.questengine.data.dao.HintDAO;
import ua.ithillel.evo.questengine.data.entity.Hint;
import ua.ithillel.evo.questengine.data.repository.HintRepository;

import java.util.List;
import java.util.Optional;

@Component
public class HintDAOImpl implements HintDAO {

    private final HintRepository hintRepository;

    @Autowired
    public HintDAOImpl(HintRepository hintRepository) {
        this.hintRepository = hintRepository;
    }

    @Override
    public Optional<Hint> getById(Long id) {
        return hintRepository.findById(id);
    }

    @Override
    public List<Hint> getAll() {
        return hintRepository.findAll();
    }

    @Override
    public void save(Hint hint) {
        hintRepository.save(hint);
    }

    @Override
    public void deleteById(Long id) {
        hintRepository.deleteById(id);
    }

}
