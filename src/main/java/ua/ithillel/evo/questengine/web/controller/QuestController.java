package ua.ithillel.evo.questengine.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ithillel.evo.questengine.data.entity.Quest;
import ua.ithillel.evo.questengine.service.QuestService;
import ua.ithillel.evo.questengine.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/quests")
public class QuestController {

    private final QuestService questService;

    @Autowired
    public QuestController(QuestService questService) {
        this.questService = questService;
    }

    @PostMapping(value = "/user/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@PathVariable(name = "id") Long id, @RequestBody Quest quest) {
        questService.createQuestByUser(id, quest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Quest>> getById(@PathVariable Long id) {
        return new ResponseEntity<>(questService.getById(id), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Quest>> getAll() {
        return new ResponseEntity<>(questService.getAll(), HttpStatus.OK);
    }

    //    placeholder for @PutMapping

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        questService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}