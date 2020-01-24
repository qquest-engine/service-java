package ua.ithillel.evo.questengine.service;


import ua.ithillel.evo.questengine.data.entity.AppUser;

import java.util.List;

public interface AppUserService {

    AppUser getById(Long id);

    AppUser getByUserName(String userName);

    AppUser getByEmail(String email);

    List<AppUser> getAll();

    void save(AppUser appUser);

    void deleteById(Long id);
}
