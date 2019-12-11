package ua.ithillel.evo.questengine.service;

import ua.ithillel.evo.questengine.data.entity.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    Optional<Question> getById(Long id);

    List<Question> getAllByQuestId(Long questId);

    void createQuestionForQuest(Long questId, Question question);

    void save(Question question);

    void deleteById(Long id);
}
