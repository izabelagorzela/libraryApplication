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
    @Column(name="idReaders")
    private int idReader;
    @NotBlank
    @Column(name="FirstName")
    private String firstName;
    @NotBlank
    @Column(name="LastName")
    private String lastName;
    @NotBlank
    @Column(name="Login")
    private String login;
    @NotBlank
    @Column(name="Password")
    private String password;
    @NotBlank
    @Column(name="Street")
    private String street;
    @NotBlank
    @Column(name="Postcode")
    private String postcode;
    @NotBlank
    @Column(name="City")
    private String city;
    @NotBlank
    @Column(name="Country")
    private String country;
    @Email
    @Column(name="Mail")
    private String mail;
    @Column(name="Telephone")
    private String telephone;




}
