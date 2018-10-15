package org.gorzela.library.common;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Iza on 2017-07-08.
 */

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Readers")
public class Reader {

    @Id
    @GeneratedValue
    @Column(name="idreaders")
    private Long readerId;

    @NotBlank
    @Column(name="firstname")
    private String firstName;

    @NotBlank
    @Column(name="lastname")
    private String lastName;

    @NotBlank
    @Column(name="login")
    private String login;

    @NotBlank
    @Column(name="password")
    private String password;

    @NotBlank
    @Column(name="street")
    private String street;

    @NotBlank
    @Column(name="postcode")
    private String postcode;

    @NotBlank
    @Column(name="city")
    private String city;

    @NotBlank
    @Column(name="country")
    private String country;

    @Email
    @Column(name="mail")
    private String mail;

    @Column(name="telephone")
    private String telephone;

    @NotBlank
    @Column(name="role")
    private String role;

    @Column(name="payment")
    private double payment;
}
