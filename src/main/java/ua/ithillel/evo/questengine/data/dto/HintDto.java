package ua.ithillel.evo.questengine.data.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HintDto {

    @NotNull(message = "Text is required")
    private String text;

    @NotNull(message = "Dduration is required")
    private LocalTime duration;

    @NotNull(message = "Answer is required")
    private String answer;
}
