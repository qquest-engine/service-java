package ua.ithillel.evo.questengine.data.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import ua.ithillel.evo.questengine.data.entity.Role;
import ua.ithillel.evo.questengine.data.entity.Type;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestDto {

    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Description is required")
    private String description;

    @NotNull(message = "Type is required")
    private Type type;

    private Integer difficulty;

    @NotNull(message = "IsPublic is required")
    private Boolean isPublic;
}
