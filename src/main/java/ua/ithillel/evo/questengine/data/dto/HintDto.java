package ua.ithillel.evo.questengine.data.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HintDto {

    @NotNull(message = "Text is required")
    private String hintText;

    @NotNull(message = "Duration is required")
    private Long duration;
}
