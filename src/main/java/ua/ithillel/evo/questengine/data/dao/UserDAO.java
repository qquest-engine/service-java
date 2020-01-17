package ua.ithillel.evo.questengine.data.dao;

import ua.ithillel.evo.questengine.data.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    User getByEmailAndPassword(String email, String password);

    User getByEmail(String email);

    Optional<User> getById(Long id);

    List<User> getAll();

    void save(User user);

    void deleteById(Long id);

}
