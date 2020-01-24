package ua.ithillel.evo.questengine.service;

import ua.ithillel.evo.questengine.data.entity.Hint;

import java.util.List;
import java.util.Optional;

public interface HintService {

    Hint getById(Long id);

    List<Hint> getAll();

//    void createHintForQuestion(Long questionId, Hint hint);

    void save(Hint hint);

    void deleteById(Long id);

    List<Hint> getCurrentHintForQuestion(Long questionId, Long questionStartTime);

//    Long getHintDurationByHintId(Long hintId);

}
