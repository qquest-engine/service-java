package ua.ithillel.evo.questengine.service.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ithillel.evo.questengine.data.dao.QuestDAO;
import ua.ithillel.evo.questengine.data.dao.UserDAO;
import ua.ithillel.evo.questengine.data.entity.Quest;
import ua.ithillel.evo.questengine.data.entity.User;
import ua.ithillel.evo.questengine.service.QuestService;

import javax.transaction.Transactional;
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
    public List<Quest> getAllQuests() {
        return questDAO.findAll();
    }

    @Override
    public Quest findById(Long id) {
        return questDAO.findById(id);
    }

    @Override
    public Quest save(Quest quest) {
        return questDAO.save(quest);
    }

    @Override
    public void delete(Quest quest) {
        questDAO.delete(quest);
    }

    @Override
    public void createQuestForUser(Long userId, Quest quest) {
        User user = userDAO.findById(userId);
        user.getQuests().add(quest);
        quest.setUser(user);
        userDAO.save(user);
    }

    @Override
    public Quest updateQuest(Long usersId, Quest quest) {
        User user = userDAO.findById(usersId);
        quest.setUser(user);
        return questDAO.save(quest);
    }
}
