package ua.ithillel.evo.questengine.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ua.ithillel.evo.questengine.data.converter.UserConverter;
import ua.ithillel.evo.questengine.data.dto.UserDto;
import ua.ithillel.evo.questengine.data.entity.AppUser;
import ua.ithillel.evo.questengine.service.UserService;
import ua.ithillel.evo.questengine.web.validation.UserValidator;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> create(@Valid @RequestBody UserDto userDto) throws Exception {
        userService.save(UserConverter.convertFromDto(userDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        AppUser appUser = userService.getById(id);
        return new ResponseEntity<>(UserConverter.convertFromEntity(appUser), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> usersDto = userService.getAll().stream().map(
                UserConverter::convertFromEntity
        ).collect(Collectors.toList());

        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@Valid @RequestBody UserDto userDto, @PathVariable Long id) throws Exception {
        AppUser appUser = userService.getById(id);
        AppUser newAppUser = UserConverter.convertFromDto(userDto);
        appUser.setEmail(newAppUser.getEmail());
        appUser.setPassword(newAppUser.getPassword());
        appUser.setRole(newAppUser.getRole());
        UserValidator.validate(appUser);
        userService.save(appUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
