package ua.ithillel.evo.questengine.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ithillel.evo.questengine.data.converter.QuestConverter;
import ua.ithillel.evo.questengine.data.dto.QuestDto;
import ua.ithillel.evo.questengine.data.entity.Quest;
import ua.ithillel.evo.questengine.service.QuestService;

import javax.validation.Valid;
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

    @PostMapping(value = "/user/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody QuestDto questDto, @PathVariable(name = "id") Long id) {
        questService.createQuestByUser(id, QuestConverter.convertFromDto(questDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestDto> getById(@PathVariable Long id) {
        Quest quest = questService.getById(id).orElse(null);
        if (quest != null) {
            return new ResponseEntity<QuestDto>(QuestConverter.convertFromEntity(quest), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<QuestDto>> getAll() {
        List<QuestDto> questsDto = questService.getAll().stream().map(
                QuestConverter::convertFromEntity
        ).collect(Collectors.toList());
         return new ResponseEntity<>(questsDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateQuest(@Valid @RequestBody QuestDto questDto, @PathVariable Long id) {
        Quest quest = questService.getById(id).orElse(null);
        Quest newQuest = QuestConverter.convertFromDto(questDto);
        if (quest != null) {
            quest.setName(newQuest.getName());
            quest.setDescription(newQuest.getDescription());
            quest.setType(newQuest.getType());
            quest.setDifficulty(newQuest.getDifficulty());
            quest.setIsPublic(newQuest.getIsPublic());
            questService.save(quest);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        questService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}