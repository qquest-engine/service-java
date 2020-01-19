package ua.ithillel.evo.questengine.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import ua.ithillel.evo.questengine.data.entity.Quest;
import ua.ithillel.evo.questengine.data.entity.Type;
import ua.ithillel.evo.questengine.data.entity.User;
import ua.ithillel.evo.questengine.service.QuestService;
import ua.ithillel.evo.questengine.service.UserService;

import java.util.List;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class QuestControllerIntegrationTest extends ControllerBaseTest {

    @Autowired
    QuestService questService;

    @Autowired
    UserService userService;

    @Test
    void whenCreateQuestThenRespondCreatedTest() {


    }
}
