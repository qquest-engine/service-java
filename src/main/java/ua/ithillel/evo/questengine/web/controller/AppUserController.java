package ua.ithillel.evo.questengine.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ithillel.evo.questengine.data.converter.UserConverter;
import ua.ithillel.evo.questengine.data.dto.UserDto;
import ua.ithillel.evo.questengine.data.entity.AppUser;
import ua.ithillel.evo.questengine.service.AppUserService;
import ua.ithillel.evo.questengine.web.validation.AppUserValidator;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> create(@Valid @RequestBody UserDto userDto) {
        appUserService.save(UserConverter.convertFromDto(userDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        AppUser appUser = appUserService.getById(id);
        return new ResponseEntity<>(UserConverter.convertFromEntity(appUser), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> usersDto = appUserService.getAll().stream().map(
                UserConverter::convertFromEntity
        ).collect(Collectors.toList());

        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@Valid @RequestBody UserDto userDto, @PathVariable Long id) {
        AppUser appUser = appUserService.getById(id);
        AppUser newAppUser = UserConverter.convertFromDto(userDto);
        appUser.setEmail(newAppUser.getEmail());
        appUser.setPassword(newAppUser.getPassword());
        appUser.setRole(newAppUser.getRole());
        AppUserValidator.validate(appUser);
        appUserService.save(appUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        appUserService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
