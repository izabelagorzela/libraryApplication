package org.gorzela.library.common;



import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Iza on 2017-07-08.
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Authors")
class Author {

    @Id
    @GeneratedValue
    @Column(name="idauthors")
    private int authorId;
    @NotBlank
    @Column(name="firstname")
    private String firstName;
    @NotBlank
    @Column(name="secondname")
    private  String lastName;
}
