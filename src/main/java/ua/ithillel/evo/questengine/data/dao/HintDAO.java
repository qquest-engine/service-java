package ua.ithillel.evo.questengine.data.dao;

import ua.ithillel.evo.questengine.data.entity.Hint;

import java.util.List;
import java.util.Optional;

public interface HintDAO {

    Optional<Hint> getById(Long id);

    List<Hint> getAll();

    void save(Hint hint);

    void deleteById(Long id);

}
