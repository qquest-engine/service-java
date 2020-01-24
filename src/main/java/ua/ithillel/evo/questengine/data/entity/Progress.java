package ua.ithillel.evo.questengine.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
@Builder
@Entity
public class Progress extends BaseEntity {

    @Column
    private Long startTime;

    @Column
    private Long endTime;

    @Column
    private Boolean successful;

    @ManyToOne(optional = false)
    @JoinColumn
    @JsonIgnore
    private Game game;

    @ManyToOne(optional = false)
    @JoinColumn
    @JsonIgnore
    private Question question;

    @PrePersist
    public void prePersist() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        startTime = timestamp.getTime();
    }
}
