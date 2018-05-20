package org.gorzela.library.common;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Reservations")
public class Reservation {

    @Id
    @GeneratedValue
    @Column(name="idreservations")
    private Long reservationId;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(name="datefrom")
    private Date dateFrom;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(name="dateto")
    private Date dateTo;

    @Temporal(TemporalType.DATE)
    @Column(name="canceldate")
    private Date cancelDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name="readers_idreaders")          //nazwa klucza obcego z tabeli loan małymi literami
    private Reader reader;

    @NotNull
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
