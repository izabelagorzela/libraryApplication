package org.gorzela.library.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Iza on 2017-07-08.
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Payments")
public class Payment {

    @Id
    @GeneratedValue
    @Column(name="idpayments")
    private int paymentId;
    @Column(name="value")
    private double value;
    @Column(name="datefrom")
    private int dateFrom;
    @Column(name="dateto")
    private int dateTo;
    @Column(name="readers_idreaders")
    private int readerId;
    @Column(name="books_idbooks")
    private int bookId;

}
