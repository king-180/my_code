package com.wx.demo.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author wangxing
 * @date 2021/4/16 15:41
 */
public class DateUtilsEx {

    public static final String DATE_FORMAT_YEAR = "yyyy";
    public static final String DATE_FORMAT_MONTH = "yyyy.MM";
    public static final String DATE_FORMAT_DAY = "yyyy.MM.dd";
    public static final String DATE_FORMAT_DAY_C = "yyyy年MM月dd日";
    public static final String DATE_FORMAT_SEC = "yyyy.MM.dd HH:mm:ss";
    public static final String DATE_FORMAT_UTC = "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'";
    public static final String DATE_FORMAT_SEC_U = "yyyyMMddHHmmss";

    /**
     * 日期格式:yyyyMMdd
     */
    public static final String DATE_FMT_YYYYMMDD = "yyyyMMdd";

    /**
     * 日期格式:yyyyMMddHHmmss
     */
    public static final String DATE_FMT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * 日期格式:yyyyMMddHHmmssSSS
     */
    public static final String DATE_FMT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    /**
     * 日期格式:yyyy-MM-dd
     */
    public static final String DATE_FMT_YYYYMMDD_WITH_HYPHEN = "yyyy-MM-dd";

    /**
     * 日期格式:yyyy-MM
     */
    public static final String DATE_FMT_YYYYMM_WITH_HYPHEN = "yyyy-MM";

    /**
     * 日期格式:MMdd
     */
    public static final String DATE_FMT_MMDD = "MMdd";

    /**
     * HHmmss
     */
    public static final String DATE_FMT_HHMMSS = "HHmmss";

    /**
     * 日期格式:yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_FMT_YYYYMMDDHHMMSS_WITH_HYPHEN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式:yyyy-MM-dd hh:mm:ss
     */
    public static final String DATE_FMT_YYYYMMDDHHMMSS_WITH_HYPHEN_TWELVE = "yyyy-MM-dd hh:mm:ss";

    /**
     * 日期格式:yyyy-MM-dd'T'HH:mm:ss
     */
    public static final String DATE_FMT_YYYYMMDDHHMMSS_WITH_HYPHEN_UTC = "yyyy-MM-dd'T'HH:mm:ss";

    /**
     * 日期格式:yyyy-MM-dd'T'HH:mm:ss.SSSZ
     */
    public static final String DATE_FMT_YYYYMMDDHHMMSS_WITH_HYPHEN_UTC_Z = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    /**
     * 日志输出
     */
    public static final String LOG_FORMAT = "%d_%s_%d";


    private DateUtilsEx() {
    }

    public static Date getFDayInMonth(Date date) {
        return DateUtils.truncate(date, 2);
    }

    public static Date getFDayInCurrentYear(Date date) {
        return DateUtils.truncate(date, 1);
    }

    public static String formatToString(Date date, String format) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }
    }

    public static Date formatToDate(String dateString, String format) throws ParseException {
        if (StringUtils.isBlank(dateString)) {
            return null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateString);
        }
    }

    public static String getDayofWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String weekday = "";
        int week_day = calendar.get(7);
        if (week_day == 1) {
            weekday = "星期天";
        } else if (week_day == 2) {
            weekday = "星期一";
        } else if (week_day == 3) {
            weekday = "星期二";
        } else if (week_day == 4) {
            weekday = "星期三";
        } else if (week_day == 5) {
            weekday = "星期四";
        } else if (week_day == 6) {
            weekday = "星期五";
        } else if (week_day == 7) {
            weekday = "星期六";
        }

        return weekday;
    }

    public static Date getEDayInCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(1), 11, 31);
        return calendar.getTime();
    }

    public static String getNextYear() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(1, 1);
        return dateFormat.format(calendar.getTime());
    }

    public static String getCurrYear() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }

    public static String getCurrMothe() {
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }

    public static String getHistoryYear() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(1, -1);
        return dateFormat.format(calendar.getTime());
    }

    public static Date addMonths(Date date, int month) {
        return DateUtils.addMonths(date, month);
    }

    public static Date getNextDate(Date date) {
        return DateUtils.addDays(date, 1);
    }

    public static Date getPreDate(Date date) {
        return DateUtils.addDays(date, -1);
    }

    public static String getDateString(int year, int month, int data, int num) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, data);
        calendar.add(2, num);
        return dateFormat.format(calendar.getTime());
    }

    public static String getDateString(int year, int month, int data) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, data);
        return dateFormat.format(calendar.getTime());
    }

    public static int getDayBetween(Date startDate, Date endDate) {
        long to = startDate.getTime();
        long end = endDate.getTime();
        return (new BigDecimal((end - to) / 86400000L)).setScale(0, RoundingMode.DOWN).intValue();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getNextDate(new Date()));
    }

}
