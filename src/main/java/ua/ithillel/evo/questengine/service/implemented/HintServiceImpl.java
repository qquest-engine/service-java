package ua.ithillel.evo.questengine.service.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ithillel.evo.questengine.data.dao.HintDAO;
import ua.ithillel.evo.questengine.data.dao.QuestionDAO;
import ua.ithillel.evo.questengine.data.entity.Hint;
import ua.ithillel.evo.questengine.data.entity.Quest;
import ua.ithillel.evo.questengine.data.entity.Question;
import ua.ithillel.evo.questengine.service.HintService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HintServiceImpl implements HintService {

    private HintDAO hintDAO;
    private QuestionDAO questionDAO;

    @Autowired
    public HintServiceImpl(HintDAO hintDAO, QuestionDAO questionDAO) {
        this.hintDAO = hintDAO;
        this.questionDAO = questionDAO;
    }

    @Override
    public Optional<Hint> getById(Long id) {
        return hintDAO.getById(id);
    }

    @Override
    public List<Hint> getAll() {
        return hintDAO.getAll();
    }

    @Override
    public void createHintForQuestion(Long questionId, Hint hint) {
        Question question = questionDAO.getById(questionId).orElse(null);//todo
        question.getHints().add(hint);
        hint.setQuestion(question);
        questionDAO.save(question);
    }

    @Override
    public void save(Hint hint) {
        hintDAO.save(hint);
    }

    @Override
    public void deleteById(Long id) {
        hintDAO.deleteById(id);
    }
}
