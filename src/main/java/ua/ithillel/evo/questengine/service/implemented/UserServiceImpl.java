package ua.ithillel.evo.questengine.service.implemented;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ithillel.evo.questengine.data.dao.UserDAO;
import ua.ithillel.evo.questengine.data.entity.User;
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
    public User getByEmailAndPassword(String email, String password) {
        return this.userDAO.getByEmailAndPassword(email, password);
    }

    @Override
    public User getByEmail(String email) {
        return this.userDAO.getByEmail(email);
    }

    @Override
    public User getById(Long id) {
        return this.userDAO.getById(id);
    }

    @Override
    public List<User> getAll() {
        return this.userDAO.getAll();
    }

    @Override
    public void save(User newUser) throws Exception {
        if (newUser.getId() != null) {
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            this.userDAO.save(newUser);
        } else {
            User userFromDb = getByEmail(newUser.getEmail());
            if (userFromDb != null) {
                throw new Exception("Email already exist!");
            }
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            this.userDAO.save(newUser);
        }
    }

    @Override
    public void deleteById(Long id) {
        this.userDAO.deleteById(id);
    }
}
