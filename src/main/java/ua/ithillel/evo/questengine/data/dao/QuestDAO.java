package ua.ithillel.evo.questengine.data.dao;

import ua.ithillel.evo.questengine.data.entity.Quest;

import java.util.List;

public interface QuestDAO {

    List<Quest> findAll();

    Quest findById(Long id);

    Quest save(Quest quest);

    void delete(Quest quest);

}
