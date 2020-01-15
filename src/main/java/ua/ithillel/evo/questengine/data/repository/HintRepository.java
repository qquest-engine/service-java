package ua.ithillel.evo.questengine.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ithillel.evo.questengine.data.entity.Hint;

import java.util.List;

@Repository
public interface HintRepository extends JpaRepository<Hint, Long> {
}
