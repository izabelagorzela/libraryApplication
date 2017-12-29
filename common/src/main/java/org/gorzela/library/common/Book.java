package org.gorzela.library.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

/**
 * Created by Iza on 2017-07-08.
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Books")
public class Book {

    @Id
    @GeneratedValue
    @Column(name="idbooks")
    private int bookId;
    @NotBlank
    @Column(name="title")
    private String title;
    @NotBlank
    @Column(name="isbn")
    private String isbn;
    @NotBlank
    @Column(name="publishinghouse")
    private String publishingHouse;
    @Column(name="publicationyear")
    private int publicationYear;
    @Column(name="categories_idcategories")
    private int categoryId;
}
