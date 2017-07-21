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
@Table(name="Loans")
public class Loan {

    @Id
    @GeneratedValue
    @Column(name="idLoans")
    private int idLoan;
    @Column(name="DateFrom")
    private int dateFrom;
    @Column(name="DateTo")
    private int dateTo;
    @Column(name="Readers_idReaders")
    private int idReader;
    @Column(name="Books_idBooks")
    private int idBook;
}
