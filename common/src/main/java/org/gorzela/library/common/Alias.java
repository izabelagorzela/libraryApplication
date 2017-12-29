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
@Table(name="Aliases")
public class Alias {

    @Id
    @GeneratedValue
    @Column(name="idaliases")
    private int aliasId;
    @Column(name="firstname")
    private String firstName;
    @NotBlank
    @Column(name="lastname")
    private String lastName;
}
