package ua.ithillel.evo.questengine.data.dao;

import ua.ithillel.evo.questengine.data.entity.Quest;

import java.util.List;

public interface QuestDAO {

    List<Quest> getAll();

    void save(Quest quest);

    void delete(Quest quest);
}
