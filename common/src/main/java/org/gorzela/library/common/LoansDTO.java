package org.gorzela.library.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class LoansDTO {

    List<Loan> loans;
}
