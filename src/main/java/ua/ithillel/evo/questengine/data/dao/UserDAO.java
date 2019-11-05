package ua.ithillel.evo.questengine.data.dao;

import ua.ithillel.evo.questengine.data.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    User getUserByEmailAndAndPassword(String email, String password);

    User getUserById(Long id);

    List<User> getAll();

    void save(User user);

    void delete(User user);

}
