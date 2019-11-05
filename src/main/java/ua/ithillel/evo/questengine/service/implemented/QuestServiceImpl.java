package ua.ithillel.evo.questengine.service.implemented;

import org.springframework.stereotype.Service;
import ua.ithillel.evo.questengine.data.dao.QuestDAO;
import ua.ithillel.evo.questengine.data.entity.Quest;
import ua.ithillel.evo.questengine.service.QuestService;

import java.util.List;

@Service
public class QuestServiceImpl implements QuestService {

    private final QuestDAO questDAO;

    public QuestServiceImpl(QuestDAO questDAO) {
        this.questDAO = questDAO;
    }

    @Override
    public List<Quest> getAll() {
        return this.questDAO.getAll();
    }

    @Override
    public void save(Quest quest) {
        this.questDAO.save(quest);
    }

    @Override
    public void delete(Quest quest) {
        this.questDAO.delete(quest);
    }
}
