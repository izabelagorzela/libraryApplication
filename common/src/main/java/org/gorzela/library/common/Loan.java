package org.gorzela.library.common;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Loans")
public class Loan {

    @Id
    @GeneratedValue
    @Column(name="idloans")
    private Long loanId;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(name="datefrom")
    private Date dateFrom;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(name="dateto")
    private Date dateTo;

    @Temporal(TemporalType.DATE)
    @Column(name="returndate")
    private Date returnDate;

    @ManyToOne
    @JoinColumn(name="readers_idreaders")          //nazwa klucza obcego z tabeli loan małymi literami
    private Reader reader;

    @ManyToOne
    @JoinColumn(name="books_idbooks")
    private Book book;

    public String getTitleName() {

        return book.getTitle();

    }

    public String getAuthorName() {

        return book.getAuthorsNames();

    }

    public String getFormatDateFrom() {

        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
        String newDateFormat = simpleDate.format(dateFrom);
        return newDateFormat;

    }

    public String getFormatDateTo() {

        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
        String newDateFormat = simpleDate.format(dateTo);
        return newDateFormat;

    }

    public String getEmptyDateFrom() {

        return "-";

    }

    public String getEmptyDateTo() {

        return "-";

    }


}
