package ua.ithillel.evo.questengine.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import ua.ithillel.evo.questengine.service.QuestService;
import ua.ithillel.evo.questengine.service.AppUserService;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class QuestControllerIntegrationTest extends ControllerBaseTest {

    @Autowired
    QuestService questService;

    @Autowired
    AppUserService appUserService;

    @Test
    void whenCreateQuestThenRespondCreatedTest() {


    }
}
