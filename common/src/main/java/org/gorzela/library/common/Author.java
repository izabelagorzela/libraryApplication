package org.gorzela.library.common;



import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * Created by Iza on 2017-07-08.
 */
@JsonIdentityInfo(property = "authorId", generator = ObjectIdGenerators.PropertyGenerator.class, scope = Author.class)
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Authors")
public class Author {

    @Id
    @GeneratedValue
    @Column(name="idauthors")
    private Long authorId;

    @NotBlank
    @Column(name="firstname")
    private String firstName;

    @NotBlank
    @Column(name="lastname")
    private  String lastName;

    @ManyToMany
    @JoinTable(
            name = "authorsaliases",
            joinColumns = @JoinColumn(name = "authors_idauthors", referencedColumnName = "idauthors"),
            inverseJoinColumns = @JoinColumn(name = "aliases_idaliases", referencedColumnName = "idaliases")
    )
    private List<Alias> aliases;

    @ManyToMany
    @JoinTable(
            name = "booksauthors",
            joinColumns = @JoinColumn(name = "authors_idauthors", referencedColumnName = "idauthors"),
            inverseJoinColumns = @JoinColumn(name = "books_idbooks", referencedColumnName = "idbooks")
    )
    private List<Book> books;


}
