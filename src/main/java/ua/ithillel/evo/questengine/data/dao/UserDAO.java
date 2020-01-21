package ua.ithillel.evo.questengine.data.dao;

import ua.ithillel.evo.questengine.data.entity.AppUser;

import java.util.List;

public interface UserDAO {

    AppUser getByEmailAndPassword(String email, String password);

    AppUser getByEmail(String email);

    AppUser getById(Long id);

    List<AppUser> getAll();

    void save(AppUser appUser);

    void deleteById(Long id);

}
