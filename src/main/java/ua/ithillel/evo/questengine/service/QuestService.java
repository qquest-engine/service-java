package ua.ithillel.evo.questengine.service;

import ua.ithillel.evo.questengine.data.entity.Quest;

import java.util.List;

public interface QuestService {

    List<Quest> getAllQuests();

    Quest findById(Long id);

    Quest save(Quest quest);

    void delete(Quest quest);

    void createQuestForUser(Long id, Quest quest);

    Quest updateQuest(Long usersId, Quest quest);
}
