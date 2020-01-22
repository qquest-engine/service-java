package ua.ithillel.evo.questengine.data.dao.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ua.ithillel.evo.questengine.data.dao.UserDAO;
import ua.ithillel.evo.questengine.data.entity.User;
import ua.ithillel.evo.questengine.data.repository.UserRepository;
import ua.ithillel.evo.questengine.exception.NotFoundException;

import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {

    private final UserRepository userRepository;

    @Autowired
    public UserDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getByEmailAndPassword(String email, String password) {
        return this.userRepository.findUserByEmailAndPassword(email, password)
                .orElseThrow(() -> new NotFoundException("Wrong credentials. Please check username and password"));
    }

    @Override
    public User getByEmail(String email) {
        return this.userRepository.findUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("There is no User with email " + email));
    }

    @Override
    public User getById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("There is no User with id " + id));
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public void save(User user) {
        this.userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }
}
