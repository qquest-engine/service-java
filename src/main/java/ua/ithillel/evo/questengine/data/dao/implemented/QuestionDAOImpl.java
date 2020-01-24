package ua.ithillel.evo.questengine.data.dao.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.ithillel.evo.questengine.data.dao.QuestionDAO;
import ua.ithillel.evo.questengine.data.entity.Question;
import ua.ithillel.evo.questengine.data.repository.QuestionRepository;
import ua.ithillel.evo.questengine.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Component
public class QuestionDAOImpl implements QuestionDAO {

    private QuestionRepository questionRepository;

    @Autowired
    public QuestionDAOImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question getById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("There is no Question with id " + id));
    }

    @Override
    public List<Question> getAllByQuestId(Long questId) {
        return questionRepository.findQuestionsByQuestIdOrderByIdAsc(questId);
    }

    @Override
    public void save(Question question) {
        questionRepository.save(question);
    }

    @Override
    public void deleteById(Long id) {
        questionRepository.deleteById(id);
    }
}
