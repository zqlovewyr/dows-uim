package org.dows.account.biz.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Administrator
 * @date 2023/2/13 14:29
 */
public class DateUtil {
    //1、获取去年今天
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
}
