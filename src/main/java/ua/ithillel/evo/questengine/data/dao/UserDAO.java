package ua.ithillel.evo.questengine.data.dao;

import ua.ithillel.evo.questengine.data.entity.User;

import java.util.Optional;

public interface UserDAO {

    Optional<User> getByUsernameAndPassword(String username, String password);

}
