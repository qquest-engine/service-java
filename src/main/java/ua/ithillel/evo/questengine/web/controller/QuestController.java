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
import ua.ithillel.evo.questengine.util.JwtUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/quests")
public class QuestController {

    private final QuestService questService;
    private final JwtUtil jwtUtil;

    @Autowired
    public QuestController(QuestService questService, JwtUtil jwtUtil) {
        this.questService = questService;
        this.jwtUtil = jwtUtil;
    }

    private Long getUserIdFromToken(String jwt_token) {
        String token = jwt_token.replace("Token:", "");
        return Long.parseLong(jwtUtil.extractClaim(token, claim -> claim.get("id")).toString());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(
            @Valid @RequestBody QuestDto questDto,
            @RequestHeader("Authorization") String jwt_token
    ) {
        Long userId = 0L;
        if (jwt_token != null && jwt_token.startsWith("Token")) {
            userId = getUserIdFromToken(jwt_token);
        }

        questService.createQuestByUser(userId, QuestConverter.convertFromDto(questDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestDto> getById(@PathVariable Long id) {
        Quest quest = questService.getById(id);
        if (quest != null) {
            return new ResponseEntity<QuestDto>(QuestConverter.convertFromEntity(quest), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<QuestDto>> getAll() {
        List<QuestDto> questsDto = questService.getPublic().stream().map(
                QuestConverter::convertFromEntity
        ).collect(Collectors.toList());
         return new ResponseEntity<>(questsDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateQuest(@Valid @RequestBody QuestDto questDto, @PathVariable Long id) {
        Quest quest = questService.getById(id);
        Quest newQuest = QuestConverter.convertFromDto(questDto);
        if (quest != null) {
            quest.setName(newQuest.getName());
            quest.setDescription(newQuest.getDescription());
            quest.setType(newQuest.getType());
            quest.setAccessTime(newQuest.getAccessTime());
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