package ua.ithillel.evo.questengine.service;


import ua.ithillel.evo.questengine.data.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User getByEmailAndPassword(String email, String password);

    User getByEmail(String email);

    User getById(Long id);

    List<User> getAll();

    void save(User user) throws Exception;

    void deleteById(Long id);
}
