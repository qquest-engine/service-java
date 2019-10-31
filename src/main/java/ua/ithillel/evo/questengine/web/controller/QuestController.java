package ua.ithillel.evo.questengine.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ithillel.evo.questengine.data.entity.Quest;
import ua.ithillel.evo.questengine.service.QuestService;
import ua.ithillel.evo.questengine.web.dto.QuestDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/quests")
public class QuestController {

    private final QuestService questService;

    @Autowired
    public QuestController(QuestService questService) {
        this.questService = questService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<QuestDTO>> getAllQuests() {
        List<QuestDTO> questDtos = questService.getAllQuests().stream()
                .map(QuestDTO::fromQuestToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(questDtos, HttpStatus.OK);
    }

    @PostMapping(value = "/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@PathVariable(name = "id") Long id, @RequestBody Quest quest) {
        questService.createQuestForUser(id, quest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "update/users/{usersId}", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity update(@PathVariable(value = "usersId")  Long usersId,
                                 @RequestBody Quest quest){
        QuestDTO questUpdateDTO = QuestDTO.fromQuestToDTO(questService.updateQuest(usersId, quest));
        return new ResponseEntity<>(questUpdateDTO, HttpStatus.OK);
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity delete(@PathVariable Long id){
//        Quest quest = questService.findById(id);
//        questService.delete(quest);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


}
