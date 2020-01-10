package ua.ithillel.evo.questengine.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.ithillel.evo.questengine.data.entity.Progress;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgressRepository extends CrudRepository<Progress, Long> {
    List<Progress> getProgressesByGame_IdOrderById(Long gameId);
}
