package ua.ithillel.evo.questengine.data.dao.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ua.ithillel.evo.questengine.data.dao.UserDAO;
import ua.ithillel.evo.questengine.data.entity.AppUser;
import ua.ithillel.evo.questengine.data.repository.AppUserRepository;

import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {

    private AppUserRepository appUserRepository;

    @Autowired
    public UserDAOImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppUser getByEmailAndPassword(String email, String password) {
        return this.appUserRepository.findUserByEmailAndPassword(email, password).orElse(null);
    }

    @Override
    public AppUser getByEmail(String email) {
        return this.appUserRepository.findUserByEmail(email).orElse(null);
    }

    @Override
    public AppUser getById(Long id) {
        return this.appUserRepository.findById(id).orElse(null);
    }

    @Override
    public List<AppUser> getAll() {
        return this.appUserRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public void save(AppUser appUser) {
        this.appUserRepository.save(appUser);
    }

    @Override
    public void deleteById(Long id) {
        this.appUserRepository.deleteById(id);
    }
}
