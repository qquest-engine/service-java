package ua.ithillel.evo.questengine.data.dao;

import ua.ithillel.evo.questengine.data.entity.Question;

import java.util.List;

public interface QuestionDAO {

    Question getById(Long id);

    List<Question> getAllByQuestId(Long questId);

    void save(Question question);

    void deleteById(Long id);

}
