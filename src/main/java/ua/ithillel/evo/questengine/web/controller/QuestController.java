package ua.ithillel.evo.questengine.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ithillel.evo.questengine.data.entity.Quest;
import ua.ithillel.evo.questengine.service.QuestService;
import ua.ithillel.evo.questengine.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/quest")
public class QuestController {

    private final QuestService questService;
    private final UserService userService;

    public QuestController(QuestService questService, UserService userService) {
        this.questService = questService;
        this.userService = userService;
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Quest>> getAll() {
        List<Quest> quests = new ArrayList<>(questService.getAll());
        return new ResponseEntity<>(quests, HttpStatus.OK);
    }

    @PostMapping(value = "/new/user/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@PathVariable(name = "id") Long id, @RequestBody Quest quest) {
        System.out.println(quest);
        quest.setUser(userService.getUserById(id));
        questService.save(quest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}