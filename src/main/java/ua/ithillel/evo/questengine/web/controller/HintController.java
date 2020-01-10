package ua.ithillel.evo.questengine.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ithillel.evo.questengine.data.entity.Hint;
import ua.ithillel.evo.questengine.service.HintService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hints")
public class HintController {

    private HintService hintService;

    @Autowired
    public HintController(HintService hintService) {
        this.hintService = hintService;
    }

    @PostMapping(value = "/question/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@PathVariable Long id, @RequestBody Hint hint) {
//        HintValidator.validate(hint);
        hintService.createHintForQuestion(id, hint);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Hint>> getById(@PathVariable Long id) {
        return new ResponseEntity<>(hintService.getById(id), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Hint>> getAll() {
        return new ResponseEntity<>(hintService.getAll(), HttpStatus.OK);
    }

    //    placeholder for @PutMapping

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        hintService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
