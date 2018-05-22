package org.gorzela.library.client.util;

import lombok.*;
import org.gorzela.library.common.Book;
import org.gorzela.library.common.Loan;
import org.gorzela.library.common.Reservation;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class SelectedBook {

    String phrasePart;
    String phraseType;
    Book selectedBook;
    Loan selectedBookCurrentLoan;
    Reservation selectedBookCurrentReservation;
}
