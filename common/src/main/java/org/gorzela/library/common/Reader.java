package org.gorzela.library.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
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




}
