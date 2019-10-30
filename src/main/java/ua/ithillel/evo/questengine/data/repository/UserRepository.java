package ua.ithillel.evo.questengine.data.repository;

import org.springframework.data.repository.CrudRepository;
import ua.ithillel.evo.questengine.data.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByEmailAndAndPassword(String email, String password);
}
