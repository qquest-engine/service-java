package ua.ithillel.evo.questengine.data.converter;

import ua.ithillel.evo.questengine.data.dto.UserDto;
import ua.ithillel.evo.questengine.data.entity.AppUser;

public class UserConverter {

    public static AppUser convertFromDto(UserDto userDto) {
        return AppUser.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .build();
    }

    public static UserDto convertFromEntity(AppUser appUser) {
        return UserDto.builder()
                .id(appUser.getId())
                .userName(appUser.getUserName())
                .email(appUser.getEmail())
                .role(appUser.getRole())
                .build();
    }
}
