package ua.ithillel.evo.questengine.data.dao.implemented;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ua.ithillel.evo.questengine.data.dao.UserDAO;
import ua.ithillel.evo.questengine.data.entity.User;
import ua.ithillel.evo.questengine.data.repository.UserRepository;

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
    public Optional<User> getByEmailAndPassword(String email, String password) {
        return this.userRepository.findUserByEmailAndPasswordOrderById(email, password);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return this.userRepository.findUserByEmailOrderById(email);
    }

    @Override
    public Optional<User> getById(Long id) {
        return this.userRepository.findById(id);
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
