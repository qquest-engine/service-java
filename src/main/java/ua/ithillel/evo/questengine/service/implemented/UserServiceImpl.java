package ua.ithillel.evo.questengine.service.implemented;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.ithillel.evo.questengine.data.dao.UserDAO;
import ua.ithillel.evo.questengine.data.entity.User;
import ua.ithillel.evo.questengine.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Optional<User> getByEmailAndAndPassword(String email, String password) {
        return this.userDAO.getByEmailAndAndPassword(email, password);
    }

    @Override
    public Optional<User> getById(Long id) {
        return this.userDAO.getById(id);
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
    public void deleteById(Long id) {
        this.userDAO.deleteById(id);
    }
}
