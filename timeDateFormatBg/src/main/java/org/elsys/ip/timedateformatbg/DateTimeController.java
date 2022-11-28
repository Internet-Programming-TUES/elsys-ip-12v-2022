package org.elsys.ip.timedateformatbg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DateTimeController {
    @Autowired
    private DateFormat dateFormat;

    @GetMapping("date")
    // GET /date?year=2022&month=11&day=21
    public String formatDate(@RequestParam int year, @RequestParam int month, @RequestParam int day) {
        // TODO: Validation
        return dateFormat.format(new Date(year, month, day));
    }

    @GetMapping("time")
    // GET /time?hour=18&minute=40
    public String formatTime(@RequestParam int hour, @RequestParam int minute) {
        // TODO: Validation
        return new StringBuilder().append(hour).append(":").append(minute).toString();
    }
}
