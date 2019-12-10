package ua.ithillel.evo.questengine.data.converter;

import ua.ithillel.evo.questengine.data.dto.UserDto;
import ua.ithillel.evo.questengine.data.entity.User;

public class UserConverter {

    public static User convertFromDto(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .build();
    }

    public static UserDto convertFromEntity(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
