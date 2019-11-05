package ua.ithillel.evo.questengine;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import ua.ithillel.evo.questengine.data.entity.Quest;
import ua.ithillel.evo.questengine.data.entity.Role;
import ua.ithillel.evo.questengine.data.entity.Type;
import ua.ithillel.evo.questengine.data.entity.User;
import ua.ithillel.evo.questengine.service.QuestService;
import ua.ithillel.evo.questengine.service.implemented.UserServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class QuestEngineApplicationTests {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    QuestService questService;

    @Test
    @SqlGroup({@Sql(scripts = {"/clear_user_app_table.sql"})})
    void whenCreateUserShouldReturnCreatedUserTest() {

        final User USER_ACTUAL = User.builder()
                .email("user@questengine.com")
                .password("password")
                .role(Role.USER)
                .build();

        userService.save(USER_ACTUAL);
        User userExpected = userService.getUserByEmailAndAndPassword(USER_ACTUAL.getEmail(), USER_ACTUAL.getPassword());

        assertEquals(userExpected.getEmail(), USER_ACTUAL.getEmail());
        assertEquals(userExpected.getPassword(), USER_ACTUAL.getPassword());
        assertEquals(userExpected.getRole(), USER_ACTUAL.getRole());
        assertNotNull(userExpected.getId());
    }

    @Test
    void whenCreateQuestShouldReturnCreatedQuestTest() {

        User user = userService.getUserById(1L);
        assertNotNull(user);
        final Quest QUEST_ACTUAL = Quest.builder()
                .name("First Quest")
                .description("Desctiption for First Quest")
                .type(Type.ONLINE)
                .difficulty(1)
                .isPublic(true)
                .user(user)
                .build();

        questService.save(QUEST_ACTUAL);
        Quest questExpected = questService.getAll().stream().filter(quest -> quest.getId() == 1).findFirst().get();

        assertEquals(questExpected.getName(), QUEST_ACTUAL.getName());
        assertEquals(questExpected.getDescription(), QUEST_ACTUAL.getDescription());
        assertEquals(questExpected.getType(), QUEST_ACTUAL.getType());
        assertEquals(questExpected.getDifficulty(), QUEST_ACTUAL.getDifficulty());
        assertEquals(questExpected.getIsPublic(), QUEST_ACTUAL.getIsPublic());
        assertEquals(questExpected.getUser().getId(), QUEST_ACTUAL.getUser().getId());
    }
}
