package ua.ithillel.evo.questengine.data.dao.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ua.ithillel.evo.questengine.data.dao.QuestDAO;
import ua.ithillel.evo.questengine.data.entity.Quest;
import ua.ithillel.evo.questengine.data.repository.QuestRepository;

import java.util.List;
import java.util.Optional;

@Component
public class QuestDAOImpl implements QuestDAO {

    private QuestRepository questRepository;

    @Autowired
    QuestDAOImpl(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    @Override
    public Quest getById(Long id) {
        return questRepository.findById(id).orElse(null);
    }

    @Override
    public List<Quest> getAll() {
        return questRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public List<Quest> getPublic() {
        return this.questRepository.getQuestsByIsPublicTrueOrderByAccessTimeDesc();
    }

    @Override
    public Quest save(Quest quest) {
        return questRepository.save(quest);
    }

    @Override
    public void deleteById(Long id) {
        questRepository.deleteById(id);
    }
}
