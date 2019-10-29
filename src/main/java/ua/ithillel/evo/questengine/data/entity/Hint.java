package ua.ithillel.evo.questengine.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.exception.DataException;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
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

    @Column(name = "shown")
    private Boolean shown;

    @Column(name = "duration")
    @Temporal(TemporalType.TIMESTAMP)
    private Date duration;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "stage_id")
    @JsonIgnore
    private Stage stage;

}
