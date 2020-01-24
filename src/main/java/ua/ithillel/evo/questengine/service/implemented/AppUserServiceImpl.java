package ua.ithillel.evo.questengine.service.implemented;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ithillel.evo.questengine.data.dao.AppUserDAO;
import ua.ithillel.evo.questengine.data.entity.AppUser;
import ua.ithillel.evo.questengine.service.AppUserService;

import java.util.List;

@Service
@Transactional
public class AppUserServiceImpl implements AppUserService {

    private final AppUserDAO appUserDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserServiceImpl(AppUserDAO appUserDAO, PasswordEncoder passwordEncoder) {
        this.appUserDAO = appUserDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser getById(Long id) {
        return this.appUserDAO.getById(id);
    }

    @Override
    public AppUser getByUserName(String userName) {
        return this.appUserDAO.getByUserName(userName);
    }

    @Override
    public AppUser getByEmail(String email) {
        return this.appUserDAO.getByEmail(email);
    }

    @Override
    public List<AppUser> getAll() {
        return this.appUserDAO.getAll();
    }

    @Override
    public void save(AppUser newAppUser) {
        newAppUser.setPassword(passwordEncoder.encode(newAppUser.getPassword()));
        this.appUserDAO.save(newAppUser);
    }

    @Override
    public void deleteById(Long id) {
        this.appUserDAO.deleteById(id);
    }
}
