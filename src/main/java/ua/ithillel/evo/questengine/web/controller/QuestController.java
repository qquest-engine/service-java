package ua.ithillel.evo.questengine.web.controller;

import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ithillel.evo.questengine.data.converter.QuestConverter;
import ua.ithillel.evo.questengine.data.dto.QuestDto;
import ua.ithillel.evo.questengine.data.entity.Quest;
import ua.ithillel.evo.questengine.service.QuestService;
import ua.ithillel.evo.questengine.service.UserService;
//import ua.ithillel.evo.questengine.security.jwt.JwtUtil;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static ua.ithillel.evo.questengine.security.SecurityConstants.HEADER_STRING;
import static ua.ithillel.evo.questengine.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/quests")
public class QuestController {

    private QuestService questService;
    private UserService userService;
//    private JwtUtil jwtUtil;

    @Autowired
    public QuestController(QuestService questService, /*JwtUtil jwtUtil,*/ UserService userService) {
        this.questService = questService;
//        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    private Long getUserIdFromToken(String jwt_token) {//this should be util method in util class
//        String token = jwt_token.replace("Bearer ", "");
//        return Long.parseLong(jwtUtil.extractClaim(token, claim -> claim.get("id")).toString());
        return JWT.decode(jwt_token).getClaim("id").asLong();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<QuestDto>> getAll(HttpServletResponse response) {
        List<QuestDto> questsDto = questService.getPublic().stream().map(
                QuestConverter::convertFromEntity
        ).collect(Collectors.toList());
        return new ResponseEntity<>(questsDto, HttpStatus.OK);
    }

    //    open method OPTIONS to endpoint /quests
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> optionsToQuests(HttpServletResponse response) {
        response.setHeader("Allow", "HEAD,GET,PUT,OPTIONS");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> create(
            @Valid @RequestBody QuestDto questDto,
            @RequestHeader(HEADER_STRING) String jwt_token
    ) {
        Long userId = 0L;
        if (jwt_token != null && jwt_token.startsWith(TOKEN_PREFIX)) {
            userId = getUserIdFromToken(jwt_token);
        }
        Quest quest = QuestConverter.convertFromDto(questDto);
        quest.setAppUser(userService.getById(userId));
        Quest savedQuest = questService.save(quest);
        return new ResponseEntity<>(savedQuest.getId(), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestDto> getById(@PathVariable Long id) {
        Quest quest = questService.getById(id);
        return new ResponseEntity<>(QuestConverter.convertFromEntity(quest), HttpStatus.OK);
    }

    @GetMapping(value = "/user/{user_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<QuestDto>> getQuestsByUserId(@PathVariable Long user_id) {
        List<Quest> quests = questService.getQuestsByUserId(user_id);
        if (quests.size() > 0) {
            return new ResponseEntity<>(
                    quests.stream().map(QuestConverter::convertFromEntity).collect(Collectors.toList()), HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); //NOT_FOUND or return empty List ?
        }
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateQuest(@Valid @RequestBody QuestDto questDto, @PathVariable Long id) {
        Quest quest = questService.getById(id);
        Quest newQuest = QuestConverter.convertFromDto(questDto);
        quest.setName(newQuest.getName());
        quest.setDescription(newQuest.getDescription());
        quest.setImageLink(questDto.getImageLink());
        quest.setType(newQuest.getType());
        quest.setAccessTime(newQuest.getAccessTime());
        quest.setIsPublic(newQuest.getIsPublic());
        questService.save(quest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        questService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}