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
@JsonIdentityInfo(property = "bookId", generator = ObjectIdGenerators.PropertyGenerator.class, scope = Book.class)
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Books")
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "idbooks")
    private Long bookId;

    @NotBlank
    @Column(name = "title")
    private String title;

    @NotBlank
    @Column(name = "isbn")
    private String isbn;

    @NotBlank
    @Column(name = "publishinghouse")
    private String publishingHouse;

    @NotBlank
    @Column(name = "publicationyear")
    private int publicationYear;

    @ManyToOne
    @JoinColumn(name = "categories_idcategories")
    private Category category;

    @ManyToMany(mappedBy = "books")
    private List<Author> authors;

    public String getAuthorsNames() {

        if (authors.size() == 1) {

            return authors.get(0).getLastName();
        } else {
            StringBuilder sb = new StringBuilder(authors.get(0).getLastName());
            for (int i = 1; i < authors.size(); i++) {
                sb.append(", ");
                sb.append(authors.get(i).getLastName());
            }
            return sb.toString();
        }
    }

    public String getCategoriesName() {

        return category.getName();
    }

    public String getAliasesNames() {

        if (authors.size() == 1) {

            List<Alias> aliases = authors.get(0).getAliases();
            if (aliases.size() == 0) {
                return "-";
            }
            if (aliases.size() == 1) {
                return aliases.get(0).getLastName();
            } else {
                StringBuilder strB = new StringBuilder(aliases.get(0).getLastName());
                for (int i = 1; i < aliases.size(); i++) {
                    strB.append(", ");
                    strB.append(aliases.get(i).getLastName());
                }
                return strB.toString();
            }
        } else {
            StringBuilder strB = new StringBuilder("");
            int counter = 0;
            for (int i = 0; i < authors.size(); i++) {
                List<Alias> aliases = authors.get(i).getAliases();
                if (aliases.size() != 0) {
                    for (int j = 0; j < aliases.size(); j++) {
                        strB.append(aliases.get(j).getLastName());
                        counter += 1;
                    }
                }
            }
            if (counter == 0) {
                return "-";
            } else {
                return strB.toString();
            }
        }
    }
}
