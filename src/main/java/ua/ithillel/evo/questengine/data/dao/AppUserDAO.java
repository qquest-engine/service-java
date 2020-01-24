package ua.ithillel.evo.questengine.data.dao;

import ua.ithillel.evo.questengine.data.entity.AppUser;

import java.util.List;

public interface AppUserDAO {

    AppUser getById(Long id);

    AppUser getByUserName(String userName);

    AppUser getByEmail(String email);

    List<AppUser> getAll();

    void save(AppUser appUser);

    void deleteById(Long id);

}
