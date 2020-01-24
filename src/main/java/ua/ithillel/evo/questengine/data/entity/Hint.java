package ua.ithillel.evo.questengine.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Hint extends BaseEntity {

    @Column
    private String hintText;

//    @Column
//    private Long previousHintId;

    @Column
    private Long duration;

    @ManyToOne(optional = false)
    @JoinColumn
    @JsonIgnore
    private Question question;

//    @OneToMany(mappedBy = "hint", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonIgnore
//    private List<Progress> progresses = new ArrayList<>();

}
