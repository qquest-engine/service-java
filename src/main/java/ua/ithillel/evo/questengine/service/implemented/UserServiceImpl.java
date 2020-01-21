package ua.ithillel.evo.questengine.service.implemented;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ithillel.evo.questengine.data.dao.UserDAO;
import ua.ithillel.evo.questengine.data.entity.AppUser;
import ua.ithillel.evo.questengine.service.UserService;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser getByEmailAndPassword(String email, String password) {
        return this.userDAO.getByEmailAndPassword(email, password);
    }

    @Override
    public AppUser getByEmail(String email) {
        return this.userDAO.getByEmail(email);
    }

    @Override
    public AppUser getById(Long id) {
        return this.userDAO.getById(id);
    }

    @Override
    public List<AppUser> getAll() {
        return this.userDAO.getAll();
    }

    @Override
    public void save(AppUser newAppUser) throws Exception {
        if (newAppUser.getId() != null) {
            newAppUser.setPassword(passwordEncoder.encode(newAppUser.getPassword()));
            this.userDAO.save(newAppUser);
        } else {
            AppUser appUserFromDb = getByEmail(newAppUser.getEmail());
            if (appUserFromDb != null) {
                throw new Exception("Email already exist!");
            }
            newAppUser.setPassword(passwordEncoder.encode(newAppUser.getPassword()));
            this.userDAO.save(newAppUser);
        }
    }

    @Override
    public void deleteById(Long id) {
        this.userDAO.deleteById(id);
    }
}
