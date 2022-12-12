package org.elsys.ip.fifaresults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class ResultsController {

    @Autowired
    private ResultsLoader resultsLoader;

    @GetMapping("/results")
    public String results(Model model) throws IOException {
        model.addAttribute("dateHelper", new DateHelper());
        model.addAttribute("matchResults", resultsLoader.getResults());

        return "results";
    }

    class DateHelper {
        public String format(LocalDateTime date, String pattern) {
            return date.format(DateTimeFormatter.ofPattern(pattern));
        }
    }
}
