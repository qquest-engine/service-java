package ua.ithillel.evo.questengine.service;

import ua.ithillel.evo.questengine.data.entity.Progress;

import java.util.List;
import java.util.Optional;

public interface ProgressService {

    Progress getById(Long id);

    List<Progress> getByGameId(Long gameId);

    List<Progress> getAll();

    void saveProgressForGame(Long gameId, Progress progress);

    void save(Progress progress);

    void deleteById(Long id);



}
