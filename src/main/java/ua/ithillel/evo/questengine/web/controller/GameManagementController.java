package ua.ithillel.evo.questengine.web.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ithillel.evo.questengine.data.converter.HintConverter;
import ua.ithillel.evo.questengine.data.converter.QuestionConverter;
import ua.ithillel.evo.questengine.data.dto.HintDto;
import ua.ithillel.evo.questengine.data.dto.QuestionDto;
import ua.ithillel.evo.questengine.data.entity.Game;
import ua.ithillel.evo.questengine.data.entity.Hint;
import ua.ithillel.evo.questengine.data.entity.Progress;
import ua.ithillel.evo.questengine.data.entity.Question;
import ua.ithillel.evo.questengine.service.GameService;
import ua.ithillel.evo.questengine.service.HintService;
import ua.ithillel.evo.questengine.service.ProgressService;
import ua.ithillel.evo.questengine.service.QuestionService;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/manage")
public class GameManagementController {

    private final QuestionService questionService;
    private final ProgressService progressService;
    private final GameService gameService;
    final HintService hintService;

    public GameManagementController(QuestionService questionService, ProgressService progressService, GameService gameService, HintService hintService) {
        this.questionService = questionService;
        this.progressService = progressService;
        this.gameService = gameService;
        this.hintService = hintService;
    }

//    @GetMapping(value = "/game/{game_id}/quest/{quest_id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<QuestionDto> getLastQuestionByQuestIdGameId(@PathVariable Long game_id, @PathVariable Long quest_id) {
//        List<Question> questions = questionService.getAllByQuestId(quest_id);
//        List<Progress> progresses = progressService.getByGameId(game_id);
//        List<Question> questionsInPogress = progresses
//                .stream()
//                .map(Progress::getQuestion)
//                .collect(Collectors.toList());
//        Optional<Game> game = Optional.of(gameService.getById(game_id)).orElse(null);
//
//        if (game.isPresent()) {
//            if (progresses.size() == 0) {
//                Progress progress = Progress.builder()
//                        .game(game.get())
//                        .question(questions.get(0))
//                        .build();
//                progressService.saveProgressForGame(game_id, progress);
//                return new ResponseEntity<>(QuestionConverter.convertFromEntity(questions.get(0)), HttpStatus.OK);
//            } else {
//                Optional<Progress> progress = progresses.stream().filter(p -> p.getEndTime() == null).findFirst();
//                if (progress.isPresent()) {
//                    return new ResponseEntity<>(QuestionConverter.convertFromEntity(progress.get().getQuestion()), HttpStatus.OK);
//                } else {
//                    for (Question q : questions) {
//                        if (!questionsInPogress.contains(q)) {
//                            progressService.saveProgressForGame(game_id, Progress.builder()
//                                    .game(game.get())
//                                    .question(q)
//                                    .build());
//                            return new ResponseEntity<>(QuestionConverter.convertFromEntity(q), HttpStatus.OK);
//                        }
//                    }
//                }
//            }
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @GetMapping(value = "/game/{game_id}/quest/{quest_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QuestionDto> getLastQuestionAndHintsByQuestIdGameId(@PathVariable Long game_id, @PathVariable Long quest_id) {
        List<Question> questions = questionService.getAllByQuestId(quest_id);
        List<Progress> progresses = progressService.getByGameId(game_id);
        List<Question> questionsInPogress = progresses
                .stream()
                .map(Progress::getQuestion)
                .collect(Collectors.toList());
        Optional<Game> optionalGame = gameService.getById(game_id);

        if (optionalGame.isPresent()) {
            if (progresses.size() == 0) {
                Progress progress = Progress.builder()
                        .game(optionalGame.get())
                        .question(questions.get(0))
                        .build();
                Long currentTime = System.currentTimeMillis();
                progressService.saveProgressForGame(game_id, progress);
                QuestionDto questionDto = QuestionConverter.convertFromEntity(questions.get(0));
                questionDto.setTimeShowNextHint(currentTime + questions.get(0).getHints().get(0).getDuration());
                questionDto.setEndTime(currentTime + questions.get(0).getDuration());
                questionDto.setHintsDto(new ArrayList<>());
                return new ResponseEntity<>(questionDto, HttpStatus.OK);
            } else {
                Optional<Progress> lastProgress = progresses.stream().filter(p -> p.getEndTime() == null).findFirst();
                if (lastProgress.isPresent()) {
                    Progress progress = lastProgress.get();
                    final List<Hint> currentHintsForQuestion = hintService.getCurrentHintForQuestion(
                            progress.getQuestion().getId(),
                            progress.getStartTime()
                    );

                    QuestionDto questionDto = QuestionConverter.convertFromEntity(progress.getQuestion());
                    List<HintDto> hintDtos = currentHintsForQuestion
                            .stream()
                            .map(HintConverter::convertFromEntity)
                            .collect(Collectors.toList());
                    Long hintDurationSum = 0L;
                    final List<Hint> hints = progress.getQuestion().getHints();
                    if (hints.size() > 0) {
                        for (Hint hintInQuestion : hints) {
                            if (currentHintsForQuestion.contains(hintInQuestion)) {
                                hintDurationSum += hintInQuestion.getDuration();
                            } else {
                                hintDurationSum += hintInQuestion.getDuration();
                                break;
                            }
                        }
                        questionDto.setTimeShowNextHint(progress.getStartTime() + hintDurationSum);
                    } else {
                        questionDto.setTimeShowNextHint(0L);
                    }
                    questionDto.setEndTime(progress.getStartTime() + progress.getQuestion().getDuration());
                    questionDto.setHintsDto(hintDtos);
                    return new ResponseEntity<>(questionDto, HttpStatus.OK);
                } else {
                    for (Question question : questions) {
                        if (!questionsInPogress.contains(question)) {
                            progressService.saveProgressForGame(game_id, Progress.builder()
                                    .game(optionalGame.get())
                                    .question(question)
                                    .build());
                            QuestionDto questionDto = QuestionConverter.convertFromEntity(question);
                            Long currentTime = System.currentTimeMillis();
                            if (question.getHints().size() > 0) {
                                questionDto.setTimeShowNextHint(currentTime + question.getHints().get(0).getDuration());
                            } else {
                                questionDto.setTimeShowNextHint(0L);
                            }
                            questionDto.setEndTime(currentTime + question.getDuration());
                            questionDto.setHintsDto(new ArrayList<>());
                            return new ResponseEntity<>(questionDto, HttpStatus.OK);
                        }
                    }
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/game/{game_id}/question/{question_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> checkAnswer(@PathVariable Long game_id,
                                            @PathVariable Long question_id,
                                            @RequestBody String answerJson) {
        ObjectMapper mapper = new ObjectMapper();
        String answer = null;
        try {
            JsonNode jsonNodeAnswer = mapper.readTree(answerJson);
            JsonNode idNode = jsonNodeAnswer.path("Answer");
            answer = idNode.asText();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Optional<Question> optionalQuestion = questionService.getById(question_id);
        Question question = null;
        if (optionalQuestion.isPresent()) {
            question = optionalQuestion.get();
        }
        String[] answers = question.getAnswer().split(";");
        for (String a : answers) {
            if (answer.equals(a)) {
                List<Progress> progress = progressService.getByGameId(game_id);
                Optional<Progress> optionalProgress = progress.stream().filter(p -> p.getQuestion().getId().equals(question_id)).findFirst();
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                if (optionalProgress.isPresent()) {
                    Progress lastProgress = optionalProgress.get();
                    if (lastProgress.getStartTime() + question.getDuration() >= System.currentTimeMillis()) {
                        lastProgress.setSuccessful(true);
                        int i = 0;
                    } else {
                        lastProgress.setSuccessful(false);
                        int i = 0;
                    }
                    if (lastProgress.getEndTime() == null) {
                        lastProgress.setEndTime(timestamp.getTime());
                        progressService.save(lastProgress);
                    }
                    return new ResponseEntity<>(HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
