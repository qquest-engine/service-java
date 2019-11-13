package ua.ithillel.evo.questengine.service;

import ua.ithillel.evo.questengine.data.entity.Quest;

import java.util.List;
import java.util.Optional;

public interface QuestService {

    Optional<Quest> getById(Long id);

    List<Quest> getAll();

    void createQuestByUser(Long userId, Quest quest);

    void save(Quest quest);

    void deleteById(Long id);
}
