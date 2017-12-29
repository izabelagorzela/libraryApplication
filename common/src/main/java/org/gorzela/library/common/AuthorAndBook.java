package org.gorzela.library.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Iza on 2017-07-08.
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="BooksAuthors")
public class AuthorAndBook implements Serializable {

    @Id
    @Column(name="authors_idauthors")
    private int authorId;

    @Id
    @Column(name="books_idbooks")
    private int bookId;
}
