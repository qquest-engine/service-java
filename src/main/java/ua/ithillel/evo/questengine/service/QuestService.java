package ua.ithillel.evo.questengine.service;

import ua.ithillel.evo.questengine.data.entity.Quest;

import java.util.List;
import java.util.Optional;

public interface QuestService {

    Quest getById(Long id);

    List<Quest> getAll();

    List<Quest> getPublic();

    Quest save(Quest quest);

    void deleteById(Long id);
}
