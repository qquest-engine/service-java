package ua.ithillel.evo.questengine.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import ua.ithillel.evo.questengine.service.UserService;


@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AppUserControllerIntegrationTest extends ControllerBaseTest {

    @Autowired
    UserService userService;

    @Test
    void whenCreateUserThenRespondCreatedTest() {

    }
}
