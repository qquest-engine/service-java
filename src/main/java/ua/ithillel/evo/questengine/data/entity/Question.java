package ua.ithillel.evo.questengine.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "question")
public class Question extends BaseEntity {

    @Column
    private String text;

    @Column
    private String answer;

    @Column
    private Long duration;

    @Column
    private String imageLink;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Hint> hints = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Progress> progresses = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn
    @JsonIgnore
    private Quest quest;
}
