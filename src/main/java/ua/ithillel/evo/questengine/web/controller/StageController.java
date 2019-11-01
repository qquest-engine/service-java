package ua.ithillel.evo.questengine.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ithillel.evo.questengine.data.entity.Stage;
import ua.ithillel.evo.questengine.service.StageService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/stages")
public class StageController {

    private StageService stageService;

    @Autowired
    public StageController(StageService stageService) {
        this.stageService = stageService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody Stage stage) {
        stageService.save(stage);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Stage> getById(@PathVariable Long id) {
        Stage result = stageService.getById(id).orElse(null);//instead of null here should be Exception or Fallback method
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Stage>> getAll() {
        return new ResponseEntity<>((List<Stage>) stageService.getAll(), HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody Stage stage) {
        stageService.save(stage);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@RequestBody Stage stage) {
        stageService.delete(stage);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
