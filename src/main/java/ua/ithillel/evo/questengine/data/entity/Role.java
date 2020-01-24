package ua.ithillel.evo.questengine.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Role extends BaseEntity {

    @Column
    private String name;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(/*name = "role_id", */referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(/*name = "user_id", */referencedColumnName = "id")})
    private Set<AppUser> users;

    @Override
    public String toString() {
        return "Role{" +
                "id: " + super.getId() + ", " +
                "name: " + name + "}";
    }

}
