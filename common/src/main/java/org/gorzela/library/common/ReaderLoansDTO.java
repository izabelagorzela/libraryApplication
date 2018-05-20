package org.gorzela.library.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReaderLoansDTO {
    Reader reader;
    Loan loan;
    Book book;
}
