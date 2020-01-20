package ua.ithillel.evo.questengine.data.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import ua.ithillel.evo.questengine.data.entity.Type;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestDto {

    private Long id;

    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Description is required")
    private String description;

    @NotNull(message = "Type is required")
    private Type type;

    @NotNull(message = "Access Time is required")
    private Long accessTime;

    @NotNull(message = "IsPublic is required")
    private Boolean isPublic;

    @NotNull(message = "Image Link is required")
    private String imageLink;

    private Long author;
}
