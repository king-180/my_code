package date;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * @author wangxing
 * @date 2021/6/16 15:24
 */
public class DateTimeFormatterDemo {

    public static void main(String[] args) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        TemporalAccessor date = dateTimeFormatter.parse("2021-06-16");
        System.out.println(date);
    }

}
