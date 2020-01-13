package ua.ithillel.evo.questengine.data.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionDto {

    @NotNull(message = "Id is required")
    private Long id;

    @NotNull(message = "Text is required")
    private String text;

    @NotNull(message = "Dduration is required")
    private Long duration;

    @NotNull(message = "Answer is required")
    private String answer;
}
