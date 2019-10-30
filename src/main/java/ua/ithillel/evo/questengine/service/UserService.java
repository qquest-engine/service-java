package ua.ithillel.evo.questengine.service;


import ua.ithillel.evo.questengine.data.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserByEmailAndAndPassword(String email, String password);

    List<User> getAll();

    void save(User user);

    void delete(User user);
}
