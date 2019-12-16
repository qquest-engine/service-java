package ua.ithillel.evo.questengine.data.converter;

import ua.ithillel.evo.questengine.data.dto.QuestDto;
import ua.ithillel.evo.questengine.data.entity.Quest;

public class QuestConverter {

    public static Quest convertFromDto(QuestDto questDto) {
        return Quest.builder()
                .name(questDto.getName())
                .description(questDto.getDescription())
                .type(questDto.getType())
                .isPublic(questDto.getIsPublic())
                .difficulty(questDto.getDifficulty())
                .imageLink(questDto.getImageLink())
                .build();
    }

    public static QuestDto convertFromEntity(Quest quest) {
        return QuestDto.builder()
                .name(quest.getName())
                .description(quest.getDescription())
                .type(quest.getType())
                .isPublic(quest.getIsPublic())
                .difficulty(quest.getDifficulty())
                .imageLink(quest.getImageLink())
                .build();
    }
}
