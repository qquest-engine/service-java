package ua.ithillel.evo.questengine.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.ithillel.evo.questengine.data.converter.QuestionConverter;
import ua.ithillel.evo.questengine.data.dto.QuestionDto;
import ua.ithillel.evo.questengine.data.entity.Game;
import ua.ithillel.evo.questengine.data.entity.Progress;
import ua.ithillel.evo.questengine.data.entity.Quest;
import ua.ithillel.evo.questengine.data.entity.Question;
import ua.ithillel.evo.questengine.exception.ApplicationGlobalException;
import ua.ithillel.evo.questengine.exception.handler.CustomGlobalExceptionHandler;
import ua.ithillel.evo.questengine.service.GameService;
import ua.ithillel.evo.questengine.service.ProgressService;
import ua.ithillel.evo.questengine.service.QuestionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/manage")
public class GameManagementController {

    private final QuestionService questionService;
    private final ProgressService progressService;
    private final GameService gameService;

    public GameManagementController(QuestionService questionService, ProgressService progressService, GameService gameService) {
        this.questionService = questionService;
        this.progressService = progressService;
        this.gameService = gameService;
    }

    @GetMapping(value = "/quest/{quest_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<QuestionDto>> getAllQuestionByQuestId(@PathVariable Long game_id, @PathVariable Long quest_id) {
        List<QuestionDto> questionDtos = questionService.getAllByQuestId(quest_id)
                .stream()
                .map(QuestionConverter::convertFromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(questionDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/game/{game_id}/quest/{quest_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestionDto> getLastQuestionByQuestIdGameId(@PathVariable Long game_id, @PathVariable Long quest_id) {
        List<Question> questions = questionService.getAllByQuestId(quest_id);
        List<Progress> progresses = progressService.getByGameId(game_id);
        List<Question> questionsInPogress = progresses
                .stream()
                .map(Progress::getQuestion)
                .collect(Collectors.toList());
        Optional<Game> game = Optional.of(gameService.getById(game_id)).orElse(null);

        if (game.isPresent()) {
            if (progresses.size() == 0) {
                Progress progress = Progress.builder()
                        .game(game.get())
                        .question(questions.get(0))
                        .build();
                progressService.saveProgressForGame(game_id, progress);
                return new ResponseEntity<>(QuestionConverter.convertFromEntity(questions.get(0)), HttpStatus.OK);
            } else {
                Optional<Progress> progress = progresses.stream().filter(p -> p.getEndTime() == null).findFirst();
                if (progress.isPresent()) {
                    return new ResponseEntity<>(QuestionConverter.convertFromEntity(progress.get().getQuestion()), HttpStatus.OK);
                } else {
                    for (Question q : questions) {
                        if (!questionsInPogress.contains(q)) {
                            progressService.saveProgressForGame(game_id, Progress.builder()
                                    .game(game.get())
                                    .question(q)
                                    .build());
                            return new ResponseEntity<>(QuestionConverter.convertFromEntity(q), HttpStatus.OK);
                        }
                    }
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
