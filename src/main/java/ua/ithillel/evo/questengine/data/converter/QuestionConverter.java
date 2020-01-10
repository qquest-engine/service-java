package ua.ithillel.evo.questengine.data.converter;

import ua.ithillel.evo.questengine.data.dto.QuestionDto;
import ua.ithillel.evo.questengine.data.entity.Question;

public class QuestionConverter {

    public static Question convertFromDto(QuestionDto questionDto) {
        return Question.builder()
                .text(questionDto.getText())
                .duration(questionDto.getDuration())
                .answer(questionDto.getAnswer())
                .build();
    }

    public static QuestionDto convertFromEntity(Question question) {
        return QuestionDto.builder()
                .text(question.getText())
                .duration(question.getDuration())
//                .answer(question.getAnswer())
                .build();
    }
}
