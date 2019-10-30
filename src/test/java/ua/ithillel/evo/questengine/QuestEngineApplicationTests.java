package ua.ithillel.evo.questengine;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.ithillel.evo.questengine.data.dao.implemented.UserDAOImpl;
import ua.ithillel.evo.questengine.data.entity.Role;
import ua.ithillel.evo.questengine.data.entity.User;
import ua.ithillel.evo.questengine.service.UserService;
import ua.ithillel.evo.questengine.service.implemented.UserServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class QuestEngineApplicationTests {

    @Autowired
    UserServiceImpl userService;

    private final User USER_ACTUAL = User.builder()
            .email("user@questengine.com")
            .password("password")
            .role(Role.USER)
            .build();

    @Test
    void whenCreateUserShouldReturnCreatedUserTest() {

        userService.save(USER_ACTUAL);
        Optional<User> userExpected = userService.getUserByEmailAndAndPassword(USER_ACTUAL.getEmail(), USER_ACTUAL.getPassword());

        userExpected.ifPresent(user -> {
            assertEquals(user.getEmail(), USER_ACTUAL.getEmail());
            assertEquals(user.getPassword(), USER_ACTUAL.getPassword());
            assertEquals(user.getRole(), USER_ACTUAL.getRole());
            assertNotNull(user.getId());

        });
    }
}
