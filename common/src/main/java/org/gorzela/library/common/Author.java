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
    @Column(name="idAuthors")
    private int idAuthor;
    @NotBlank
    @Column(name="FirstName")
    private String firstName;
    @NotBlank
    @Column(name="SecondName")
    private  String lastName;
}
