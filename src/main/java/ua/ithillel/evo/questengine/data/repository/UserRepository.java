package ua.ithillel.evo.questengine.data.repository;

import org.springframework.data.repository.CrudRepository;
import ua.ithillel.evo.questengine.data.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
