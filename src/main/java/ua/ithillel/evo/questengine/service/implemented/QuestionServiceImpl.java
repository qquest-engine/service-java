package ua.ithillel.evo.questengine.service.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ithillel.evo.questengine.data.dao.QuestDAO;
import ua.ithillel.evo.questengine.data.dao.QuestionDAO;
import ua.ithillel.evo.questengine.data.entity.Quest;
import ua.ithillel.evo.questengine.data.entity.Question;
import ua.ithillel.evo.questengine.service.QuestionService;

import java.util.List;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDAO questionDAO;
    private final QuestDAO questDAO;

    @Autowired
    public QuestionServiceImpl(QuestionDAO questionDAO, QuestDAO questDAO) {
        this.questionDAO = questionDAO;
        this.questDAO = questDAO;
    }

    @Override
    public Question getById(Long id) {
        return questionDAO.getById(id);
    }

    @Override
    public List<Question> getAllByQuestId(Long questId) {
        return questionDAO.getAllByQuestId(questId);
    }

    @Override
    public void createQuestionForQuest(Long questId, Question question) {
        Quest quest = questDAO.getById(questId);
        quest.getQuestions().add(question);
        question.setQuest(quest);
        questDAO.save(quest);
    }

    @Override
    public void save(Question question) {
        questionDAO.save(question);
    }

    @Override
    public void deleteById(Long id) {
        questionDAO.deleteById(id);
    }
}
