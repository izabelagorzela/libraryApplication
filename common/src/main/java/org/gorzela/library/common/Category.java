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
@Table(name="Categories")
public class Category {

    @Id
    @GeneratedValue
    @Column(name="idcategories")
    private Long categoryId;

    @NotBlank
    @Column(name="name")
    private String name;
}
