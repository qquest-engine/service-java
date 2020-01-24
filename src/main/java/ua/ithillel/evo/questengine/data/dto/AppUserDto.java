package ua.ithillel.evo.questengine.data.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import ua.ithillel.evo.questengine.data.entity.Role;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppUserDto {

    private Long id;

    @NotNull(message = "Username is required")
    private String userName;

    private String email;

    @NotNull(message = "Password is required")
    private String password;

//    @NotNull(message = "Role is required")
//    private Role roles;
}
