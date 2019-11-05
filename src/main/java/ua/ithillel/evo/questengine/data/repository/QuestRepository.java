package ua.ithillel.evo.questengine.data.repository;

import org.springframework.data.repository.CrudRepository;
import ua.ithillel.evo.questengine.data.entity.Quest;

public interface QuestRepository extends CrudRepository<Quest, Long> {
}
