package ua.ithillel.evo.questengine.service.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ithillel.evo.questengine.data.dao.QuestDAO;
import ua.ithillel.evo.questengine.data.dao.UserDAO;
import ua.ithillel.evo.questengine.data.entity.Quest;
import ua.ithillel.evo.questengine.data.entity.User;
import ua.ithillel.evo.questengine.service.QuestService;

import java.util.List;

@Service
@Transactional
public class QuestServiceImpl implements QuestService {

    private final QuestDAO questDAO;
    private final UserDAO userDAO;

    @Autowired
    public QuestServiceImpl(QuestDAO questDAO, UserDAO userDAO) {
        this.questDAO = questDAO;
        this.userDAO = userDAO;
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
    public void createQuestByUser(Long userId, Quest quest) {
        User user = userDAO.getById(userId);
        user.getQuests().add(quest);
        quest.setUser(user);
        this.userDAO.save(user);
    }

    @Override
    public void save(Quest quest) {
        this.questDAO.save(quest);
    }

    @Override
    public void deleteById(Long id) {
        this.questDAO.deleteById(id);
    }
}
