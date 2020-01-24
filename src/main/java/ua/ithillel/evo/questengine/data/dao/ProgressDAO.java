package ua.ithillel.evo.questengine.data.dao;

import ua.ithillel.evo.questengine.data.entity.Progress;

import java.util.List;

public interface ProgressDAO {

    Progress getById(Long id);

    List<Progress> getByGameId(Long gameId);

    List<Progress> getAll();

    void save(Progress progress);

    void deleteById(Long id);

}
