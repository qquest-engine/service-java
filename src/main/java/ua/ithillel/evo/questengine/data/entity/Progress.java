package ua.ithillel.evo.questengine.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
@Builder
@Entity
@Table(name = "progress")
public class Progress {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Column(name = "successful")
    private Boolean successful;

    @ManyToOne(optional = false)
    @JoinColumn(name = "game_id")
    @JsonIgnore
    private Game game;

    @ManyToOne(optional = false)
    @JoinColumn(name = "hint_id")
    @JsonIgnore
    private Hint hint;
}
