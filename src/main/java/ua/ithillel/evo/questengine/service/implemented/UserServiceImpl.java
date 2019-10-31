package ua.ithillel.evo.questengine.service.implemented;


import org.springframework.stereotype.Service;
import ua.ithillel.evo.questengine.data.dao.UserDAO;
import ua.ithillel.evo.questengine.data.entity.User;
import ua.ithillel.evo.questengine.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Optional<User> getUserByEmailAndAndPassword(String email, String password) {
        return this.userDAO.getUserByEmailAndAndPassword(email, password);
    }

    @Override
    public List<User> getAll() {
        return this.userDAO.getAll();
    }

    @Override
    public void save(User user) {
        this.userDAO.save(user);
    }

    @Override
    public void delete(User user) {
        this.userDAO.delete(user);
    }

    @Override
    public User findById(Long id) {
        return userDAO.findById(id);
    }
}
