package org.dows.account.biz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Administrator
 * @date 2023/2/13 14:29
 */
public class DateUtil {
    //1、获取昨天
    public static Date getYesterday(){
        Date date = new Date();//获取当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);//当前时间减去一天，即昨天
        return calendar.getTime();
    }

    //2、date转换为String
    public static String formatDateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(date);
        return str;
    }

    //3、String转换为date
    public static Date formatStringToDate(String str){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return date;
    }

    //4、获取上周周一
    public static String getLastMonday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        // 设置周一为第一天
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        // 日期减去7天
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return sdf.format(calendar.getTime()) + " 00:00:00";
    }

    //5、获取上周周日
    public static String getLastSunday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        // 设置周一为第一天
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        // 日期减去7天
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return sdf.format(calendar.getTime()) + " 23:59:59";
    }

    //6、获取去年上周周一
    public static String getLastYearMonday(){
        Date date = new Date();//获取当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, -1);//当前时间减去一年，即去年
        //获得当前日期属于今年的第几周
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        //获取去年上一周
        calendar.set(Calendar.WEEK_OF_YEAR, weekOfYear -1 );
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        String start = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" +
                calendar.get(Calendar.DAY_OF_MONTH);
        Date date1= java.sql.Date.valueOf(start);
        return sdf.format(date1) + " 00:00:00";
    }

    //7、获取某一年的某一周的周日日期
    public static String getLastYearSunday(){
        Date date = new Date();//获取当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, -1);//当前时间减去一年，即去年
        //获得当前日期属于今年的第几周
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        //获取去年上一周
        calendar.set(Calendar.WEEK_OF_YEAR, weekOfYear -1 );
        calendar.add(Calendar.DAY_OF_WEEK, 6);
        //获得SimpleDateFormat类，我们转换为yyyy-MM-dd的时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        String start = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" +
                calendar.get(Calendar.DAY_OF_MONTH);
        Date date1= java.sql.Date.valueOf(start);
        return sdf.format(date1) + " 23:59:59";
    }

    //8、最近七天
    public static Date getLast7Days(){
        Date date = new Date();//获取当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -7);
        return calendar.getTime();
    }

    //9、最近几个月
    public static Date getLastMonthByTime(Integer month){
        Date date = new Date();//获取当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    //10、最近一年
    public static Date getLastYear(Integer year){
        Date date = new Date();//获取当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

}
