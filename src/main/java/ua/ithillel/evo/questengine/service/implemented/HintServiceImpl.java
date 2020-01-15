package ua.ithillel.evo.questengine.service.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ithillel.evo.questengine.data.dao.HintDAO;
import ua.ithillel.evo.questengine.data.dao.QuestionDAO;
import ua.ithillel.evo.questengine.data.entity.Hint;
import ua.ithillel.evo.questengine.data.entity.Question;
import ua.ithillel.evo.questengine.service.HintService;

import java.util.ArrayList;
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
        return this.hintDAO.getById(id);
    }

    @Override
    public List<Hint> getAll() {
        return this.hintDAO.getAll();
    }

//    @Override
//    public void createHintForQuestion(Long questionId, Hint hint) {
//        Question question = questionDAO.getById(questionId).orElse(null);
//        question.getHints().add(hint);
//        hint.setQuestion(question);
//        questionDAO.save(question);
//    }

    @Override
    public void save(Hint hint) {
        this.hintDAO.save(hint);
    }

    @Override
    public void deleteById(Long id) {
        this.hintDAO.deleteById(id);
    }

    @Override
    public List<Hint> getCurrentHintForQuestion(Long questionId, Long questionStartTime) {
        final long currentTime = System.currentTimeMillis();
        final Optional<Question> optionalQuestion = this.questionDAO.getById(questionId);
        List<Hint> hintsForUserShow = new ArrayList<>();
        if (optionalQuestion.isPresent()) {
            List<Hint> hints = optionalQuestion.get().getHints();
            for (Hint h : hints) {
                if (questionStartTime + h.getDuration() < currentTime) {
                    hintsForUserShow.add(h);
                    questionStartTime += h.getDuration();
                }
            }
        }
        return hintsForUserShow;
    }


    //    @Override
//    public Long getHintDurationByHintId(Long hintId) {
//        final Optional<Hint> optionalHint = hintDAO.getById(hintId);
//        return optionalHint.map(Hint::getDuration).orElse(null);
//    }
}
