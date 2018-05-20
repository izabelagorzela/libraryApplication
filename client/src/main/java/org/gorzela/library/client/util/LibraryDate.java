package org.gorzela.library.client.util;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class LibraryDate {

    //private Date currentDate;

    private Date prolongedDate;

    public String getProlongedDateAsString() {

        LocalDate todayPlusOne = LocalDate.now().plusMonths(1);
        Date prolongedDate = Date.from(todayPlusOne.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        //String prolongedDateAsString = formatter.format(prolongedDate);
        return formatter.format(prolongedDate);
    }
}
