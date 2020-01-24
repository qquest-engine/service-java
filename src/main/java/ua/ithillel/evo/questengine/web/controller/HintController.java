package ua.ithillel.evo.questengine.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ithillel.evo.questengine.data.converter.HintConverter;
import ua.ithillel.evo.questengine.data.dto.HintDto;
import ua.ithillel.evo.questengine.data.entity.Hint;
import ua.ithillel.evo.questengine.data.entity.Question;
import ua.ithillel.evo.questengine.service.HintService;
import ua.ithillel.evo.questengine.service.QuestionService;

import java.util.Optional;

@RestController
@RequestMapping("/hints")
public class HintController {

    private HintService hintService;
    private QuestionService questionService;

    @Autowired
    public HintController(HintService hintService, QuestionService questionService) {
        this.hintService = hintService;
        this.questionService = questionService;
    }

    @PostMapping(value = "/question/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@PathVariable Long id, @RequestBody HintDto hintDto) {
//        HintValidator.validate(hint);
        Hint hint = HintConverter.convertFromDto(hintDto);
        final Question question = questionService.getById(id);
        hint.setQuestion(question);
        hintService.save(hint);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> edit(@PathVariable Long id, @RequestBody HintDto hintDto) {
//        HintValidator.validate(hint);
        Hint newHint = HintConverter.convertFromDto(hintDto);
        Hint hint = hintService.getById(id);
        hint.setDuration(newHint.getDuration());
        hint.setHintText(newHint.getHintText());
        hintService.save(hint);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        hintService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
