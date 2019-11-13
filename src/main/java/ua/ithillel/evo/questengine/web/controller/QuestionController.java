package ua.ithillel.evo.questengine.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ithillel.evo.questengine.data.entity.Question;
import ua.ithillel.evo.questengine.service.QuestionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping(value = "/quest/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@PathVariable Long id, @RequestBody Question question) {
//        QuestionValidator.validate(question);
        questionService.createQuestionForQuest(id, question);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Question>> getById(@PathVariable Long id) {
        return new ResponseEntity<>(questionService.getById(id), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Question>> getAll() {
        return new ResponseEntity<>(questionService.getAll(), HttpStatus.OK);
    }

    //    placeholder for @PutMapping

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        questionService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
