package org.elsys.ip.timedateformatbg;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DateFormatTest {

    DateFormat dateFormat = new DateFormat();

    @Test
    void format() {
        String date = dateFormat.format(new Date(2001,1,1));
        assertThat(date).isEqualTo("първи януари две хиляди и първа");

        String date1 = dateFormat.format(new Date(2222,5,22));
        assertThat(date1).isEqualTo("двадесет и втори май две хиляди двеста двадесет и втора");

        String date2 = dateFormat.format(new Date(1800,8,30));
        assertThat(date2).isEqualTo("тридесети август хиляда и осемстотна");

        String date3 = dateFormat.format(new Date(2000,11,11));
        assertThat(date3).isEqualTo("единадесети ноември две хилядна");

        String date4 = dateFormat.format(new Date(1300,9,17));
        assertThat(date4).isEqualTo("седемнадесети септември хиляда и тристна");
    }
}