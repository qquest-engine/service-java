package ua.ithillel.evo.questengine.data.dao.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.ithillel.evo.questengine.data.dao.UserDAO;
import ua.ithillel.evo.questengine.data.entity.User;
import ua.ithillel.evo.questengine.data.repository.UserRepository;
import ua.ithillel.evo.questengine.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDAOImpl implements UserDAO {

    private UserRepository userRepository;

    @Autowired
    public UserDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserByEmailAndAndPassword(String email, String password) {
        return this.userRepository.findUserByEmailAndAndPassword(email, password);
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        this.userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public void save(User user) {
        this.userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        this.userRepository.delete(user);
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(()
                -> new NotFoundException(String.format("Can find user with id '%id'", id)));
    }
}
