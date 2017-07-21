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
    @Column(name="idBooks")
    private int idBook;
    @NotBlank
    @Column(name="Title")
    private String title;
    @NotBlank
    @Column(name="ISBN")
    private String isbn;
    @NotBlank
    @Column(name="PublishingHouse")
    private String publishingHouse;
    @Column(name="PublicationYear")
    private int publicationYear;
    @Column(name="Categories_idCategories")
    private int idCategory;
}
