package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author wangxing
 * @date 2021/4/3 13:19
 */
public class CalendarDemo {
    public static void main(String[] args) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2021-03-11 15:55:55");
        System.out.println(date);
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        System.out.println(date);
        calendar.setTime(new Date());
        System.out.println(calendar);
        System.out.println(calendar.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        System.out.println(calendar.getTime());
    }
}
