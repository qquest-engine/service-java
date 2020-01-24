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
public class Quest extends BaseEntity {

    @Column
    private String name;

    @Column
    private String description;

//    @Column(name = "type")
//    @Enumerated(EnumType.STRING)
//    private Type type;

    @Column
    private Long accessTime;

    @Column
    private Boolean isPublic;

    @Column
    private String imageLink;

    @OneToMany(mappedBy = "quest", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    @JsonIgnore
    private List<Question> questions = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn
    @JsonIgnore
    private AppUser author;
}
