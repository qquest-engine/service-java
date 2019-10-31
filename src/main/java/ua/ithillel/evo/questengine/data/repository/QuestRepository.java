package ua.ithillel.evo.questengine.data.repository;

import org.springframework.data.repository.CrudRepository;
import ua.ithillel.evo.questengine.data.entity.Quest;

import java.util.Optional;

public interface QuestRepository extends CrudRepository<Quest, Long> {

}
