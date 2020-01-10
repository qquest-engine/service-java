package ua.ithillel.evo.questengine.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ithillel.evo.questengine.data.entity.Progress;
import ua.ithillel.evo.questengine.service.ProgressService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/progresses")
public class ProgressController {

    private ProgressService progressService;

    @Autowired
    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @PostMapping(value = "/game/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@PathVariable Long id, @RequestBody Progress progress) {
//        ProgressValidator.validate(progress);
        progressService.saveProgressForGame(id, progress);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Progress>> getById(@PathVariable Long id) {
        return new ResponseEntity<>(progressService.getById(id), HttpStatus.OK);
    }

//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<Progress>> getAll() {
//        return new ResponseEntity<>(progressService.getAll(), HttpStatus.OK);
//    }

    //    placeholder for @PutMapping

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        progressService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
