package ua.ithillel.evo.questengine.web.dto;

import lombok.Builder;
import lombok.Data;
import ua.ithillel.evo.questengine.data.entity.Quest;
import ua.ithillel.evo.questengine.data.entity.Type;

@Data
@Builder
public class QuestDTO {
    private Long id;

    private String name;

    private String description;

    //    private Type type;
    private String type;

    private Integer difficulty;

    private Boolean general;

    private String user;

    public static QuestDTO fromQuestToDTO(Quest quest) {
        return QuestDTO.builder()
                .id(quest.getId())
                .name(quest.getName())
                .description(quest.getDescription())
                .type(quest.getType().toString())
                .difficulty(quest.getDifficulty())
                .general(quest.getGeneral())
                .user(quest.getUser().getEmail()).build();
    }
}
