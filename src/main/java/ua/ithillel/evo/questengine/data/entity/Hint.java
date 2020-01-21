package ua.ithillel.evo.questengine.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
@Builder
@Entity
@Table(name = "hint")
public class Hint {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "hint_text")
    private String hintText;

    @Column(name = "previous_hint_id")
    private Long previousHintId;

    @Column(name = "duration")
    private Long duration;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id")
    @JsonIgnore
    private Question question;

//    @OneToMany(mappedBy = "hint", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonIgnore
//    private List<Progress> progresses = new ArrayList<>();

}
