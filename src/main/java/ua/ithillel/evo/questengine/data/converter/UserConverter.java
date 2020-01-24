package ua.ithillel.evo.questengine.data.converter;

import ua.ithillel.evo.questengine.data.dto.AppUserDto;
import ua.ithillel.evo.questengine.data.entity.AppUser;

public class UserConverter {

    public static AppUser convertFromDto(AppUserDto appUserDto) {
        return AppUser.builder()
                .email(appUserDto.getEmail())
                .password(appUserDto.getPassword())
//                .roles(appUserDto.getRole())
                .userName(appUserDto.getUserName())
                .build();
    }

    public static AppUserDto convertFromEntity(AppUser appUser) {
        return AppUserDto.builder()
                .id(appUser.getId())
                .userName(appUser.getUserName())
                .email(appUser.getEmail())
//                .role(appUser.getRole())
                .build();
    }
}
