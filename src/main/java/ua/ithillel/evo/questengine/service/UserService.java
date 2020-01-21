package ua.ithillel.evo.questengine.service;


import ua.ithillel.evo.questengine.data.entity.AppUser;

import java.util.List;

public interface UserService {

    AppUser getByEmailAndPassword(String email, String password);

    AppUser getByEmail(String email);

    AppUser getById(Long id);

    List<AppUser> getAll();

    void save(AppUser appUser) throws Exception;

    void deleteById(Long id);
}
