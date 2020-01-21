package ua.ithillel.evo.questengine.data.dao;

import ua.ithillel.evo.questengine.data.entity.Quest;

import java.util.List;
import java.util.Optional;

public interface QuestDAO {

    Quest getById(Long id);

    List<Quest> getAll();

    List<Quest> getPublic();

    List<Quest> getQuestsByUserId(Long userId);

    Quest save(Quest quest);

    void deleteById(Long id);
}
