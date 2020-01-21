package ua.ithillel.evo.questengine.data.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionDto {

    private Long id;

    @NotNull(message = "Text is required")
    private String text;

    @NotNull(message = "Duration is required")
    private Long duration;

    @NotNull(message = "Answer is required")
    private String answer;

    private Long endTime;

    private List<HintDto> hintsDto;

    private Long timeShowNextHint;
}
