package org.gorzela.library.server;

import lombok.extern.slf4j.Slf4j;
import org.gorzela.library.common.Loan;
import org.gorzela.library.common.Reader;
import org.gorzela.library.server.repository.LoanRepository;
import org.gorzela.library.server.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

@Component
@Slf4j
public class UpdateInterest {

    private ArrayList<Loan> loansWithPayments;

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    ReaderRepository readerRepository;

    //every day at midnight
    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void updateAllInterest() {

        Date toDayDate = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        loansWithPayments = loanRepository.findByDateToBeforeAndReturnDateIsNull(toDayDate);

        if (loansWithPayments.size() != 0) {

            ArrayList<Reader> readerListWithPayment = new ArrayList<Reader>();
            log.info("Interest update started...");
            for (int i = 0; i < loansWithPayments.size(); i++) {

                double newValue = loansWithPayments.get(i).getReader().getPayment() + 0.5;
                loansWithPayments.get(i).getReader().setPayment(newValue);
                readerListWithPayment.add(loansWithPayments.get(i).getReader());
            }

            readerRepository.save(readerListWithPayment);

        }
    }
}

