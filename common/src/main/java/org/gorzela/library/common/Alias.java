package org.gorzela.library.common;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Iza on 2017-07-08.
 */

@JsonIdentityInfo(property = "aliasId", generator = ObjectIdGenerators.PropertyGenerator.class, scope = Alias.class)
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Aliases")
public class Alias {

    @Id
    @GeneratedValue
    @Column(name="idaliases")
    private Long aliasId;

    @Column(name="firstname")
    private String firstName;

    @NotBlank
    @Column(name="lastname")
    private String lastName;

    @ManyToMany(mappedBy="aliases")          //nazwa listy na która mapujemy z drugiej czesci relacji
    private List<Author> authors;

}
