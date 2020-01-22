package ua.ithillel.evo.questengine.data.dao.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ua.ithillel.evo.questengine.data.dao.QuestDAO;
import ua.ithillel.evo.questengine.data.entity.Quest;
import ua.ithillel.evo.questengine.data.repository.QuestRepository;
import ua.ithillel.evo.questengine.exception.NotFoundException;

import java.util.List;

@Component
public class QuestDAOImpl implements QuestDAO {

    private final QuestRepository questRepository;

    @Autowired
    QuestDAOImpl(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    @Override
    public Quest getById(Long id) {
        return questRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("There is no Quest with id " + id));
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
    public List<Quest> getQuestsByUserId(Long userId) {
        return this.questRepository.getQuestsByUserIdOrderByIdAsc(userId);
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
