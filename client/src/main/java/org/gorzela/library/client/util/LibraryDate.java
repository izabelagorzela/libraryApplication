package org.gorzela.library.client.util;

import org.springframework.stereotype.Component;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class LibraryDate {

    public String getProlongedDateAsString() {

        LocalDate todayPlusOne = LocalDate.now().plusMonths(1);
        Date prolongedDate = Date.from(todayPlusOne.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(prolongedDate);
    }

    public String getReservationFinishDateAsString() {

        LocalDate todayPlusOne = LocalDate.now().plusWeeks(1);
        Date finishDate = Date.from(todayPlusOne.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(finishDate);
    }

    public String getTodayDateAsString() {

        Date toDayDate = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(toDayDate);
    }
}
