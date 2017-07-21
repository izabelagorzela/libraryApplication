package org.gorzela.library.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Iza on 2017-07-08.
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="AuthorsAliases")
public class AuthorAndAlias {

    @Column(name="Authors_idAuthors")
    private int idAuthor;
    @Column(name="Aliases_idAliases")
    private int idAlias;
}
