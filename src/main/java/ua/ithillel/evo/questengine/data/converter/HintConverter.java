package ua.ithillel.evo.questengine.data.converter;

import ua.ithillel.evo.questengine.data.dto.HintDto;
import ua.ithillel.evo.questengine.data.entity.Hint;

public class HintConverter {

    public static Hint convertFromDto(HintDto hintDto) {
        return Hint.builder()
                .hintText(hintDto.getHintText())
                .duration(hintDto.getDuration())
                .build();
    }

    public static HintDto convertFromEntity(Hint hint) {
        return HintDto.builder()
                .hintText(hint.getHintText())
//                .duration(hint.getDuration())
                .build();
    }
}
