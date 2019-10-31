package ua.ithillel.evo.questengine.data.dao.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.ithillel.evo.questengine.data.dao.QuestDAO;
import ua.ithillel.evo.questengine.data.entity.Quest;
import ua.ithillel.evo.questengine.data.repository.QuestRepository;
import ua.ithillel.evo.questengine.exception.NotFoundException;
import ua.ithillel.evo.questengine.web.dto.QuestDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestDAOImpl implements QuestDAO {

    private QuestRepository questRepository;

    @Autowired
    public QuestDAOImpl(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    @Override
    public List<Quest> findAll() {
        return (ArrayList)this.questRepository.findAll();
    }

    @Override
    public Quest findById(Long id) {
        return this.questRepository.findById(id).orElseThrow(()
                -> new NotFoundException(String.format("Quest with id '%s' not found",id)));
    }

    @Override
    public Quest save(Quest quest) {
        this.questRepository.save(quest);
        return quest;
    }

    @Override
    public void delete(Quest quest) {
        this.questRepository.delete(quest);
    }

}
