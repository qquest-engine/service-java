package ua.ithillel.evo.questengine.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ithillel.evo.questengine.data.entity.Game;
import ua.ithillel.evo.questengine.service.GameService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping(value = "/user/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Void> create(@PathVariable Long id, @RequestBody Game game) {
    public ResponseEntity<Void> create(@PathVariable Long id) {
//        GameValidator.validate(game);
        Game game = Game.builder().build();
        gameService.createGameForUser(id, game);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Game> getById(@PathVariable Long id) {
        return new ResponseEntity<>(gameService.getById(id), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Game>> getAll() {
        return new ResponseEntity<>(gameService.getAll(), HttpStatus.OK);
    }

    //    placeholder for @PutMapping

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        gameService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
