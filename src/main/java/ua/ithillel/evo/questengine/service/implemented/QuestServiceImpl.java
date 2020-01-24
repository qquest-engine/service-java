package ua.ithillel.evo.questengine.service.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ithillel.evo.questengine.data.dao.QuestDAO;
import ua.ithillel.evo.questengine.data.dao.AppUserDAO;
import ua.ithillel.evo.questengine.data.entity.Quest;
import ua.ithillel.evo.questengine.service.QuestService;

import java.util.List;

@Service
@Transactional
public class QuestServiceImpl implements QuestService {

    private final QuestDAO questDAO;

    @Autowired
    public QuestServiceImpl(QuestDAO questDAO, AppUserDAO appUserDAO) {
        this.questDAO = questDAO;
    }

    @Override
    public Quest getById(Long id) {
        return this.questDAO.getById(id);
    }

    @Override
    public List<Quest> getAll() {
        return this.questDAO.getAll();
    }

    @Override
    public List<Quest> getPublic() {
        return this.questDAO.getPublic();
    }

    @Override
    public List<Quest> getQuestsByUserId(Long userId) {
        return this.questDAO.getQuestsByUserId(userId);
    }

    @Override
    public Quest save(Quest quest) {
        return this.questDAO.save(quest);
    }

    @Override
    public void deleteById(Long id) {
        this.questDAO.deleteById(id);
    }
}
