package ua.ithillel.evo.questengine.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ithillel.evo.questengine.data.entity.Quest;

import java.util.List;

@Repository
public interface QuestRepository extends JpaRepository<Quest, Long> {
    List<Quest> getQuestsByIsPublicTrueOrderByAccessTimeDesc();
    List<Quest> getQuestsByUserIdOrderByIdAsc(Long userId);
}
