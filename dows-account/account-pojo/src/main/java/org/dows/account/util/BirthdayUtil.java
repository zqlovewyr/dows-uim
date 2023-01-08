package org.dows.account.util;

import java.time.LocalDate;
import java.util.ArrayList;

public class BirthdayUtil {
    //根据生日计算生肖,年龄,星座
    private final static int[] dayArr = new int[] { 20, 19, 21, 20, 21, 22, 23,
            23, 23, 24, 23, 22 };
    private final static ArrayList<String> constellationList = new ArrayList<>();//存放星座的集合
    static {
        constellationList.add(0, "水瓶座");
        constellationList.add(1, "双鱼座");
        constellationList.add(2, "白羊座");
        constellationList.add(3, "金牛座");
        constellationList.add(4, "双子座");
        constellationList.add(5, "巨蟹座");
        constellationList.add(6, "狮子座");
        constellationList.add(7, "处女座");
        constellationList.add(8, "天秤座");
        constellationList.add(9, "天蝎座");
        constellationList.add(10, "射手座");
        constellationList.add(11, "魔羯座");
    }

    //获得年龄
    public static Integer getAge(LocalDate birthday){
        if (birthday == null) {
            return null;
        }
        return LocalDate.now().getYear() - birthday.getYear();
    }

    //获得生肖
    public static String getChineseZodiac(LocalDate birthday) {
        if (birthday == null){
            return null;
        }

        int year = birthday.getYear();
        if (year < 1900) {
            return null;
        }
        int start = 1900;
        String[] years = new String[] { "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊",
                "猴", "鸡", "狗", "猪" };
        return years[(year - start) % 12];
    }

    //获得星座
    /**
     * 传入日期，返回星座
     */
    public static String getConstellation(LocalDate birthday) {
        if (birthday == null){
            return null;
        }
        String constellation = "";

        int month = birthday.getMonthValue();
        int day = birthday.getDayOfMonth();
        switch (month) {
            case 1:
                //Capricorn 摩羯座（12月22日～1月20日）
                constellation = day <= 20 ? constellationList.get(11) : constellationList.get(0);
                break;
            case 2:
                //Aquarius 水瓶座（1月21日～2月19日）
                constellation = day <= 19 ? constellationList.get(0) : constellationList.get(1);
                break;
            case 3:
                //Pisces 双鱼座（2月20日～3月20日）
                constellation = day <= 20 ? constellationList.get(1) : constellationList.get(2);
                break;
            case 4:
                //白羊座 3月21日～4月20日
                constellation = day <= 20 ? constellationList.get(2) : constellationList.get(3);
                break;
            case 5:
                //金牛座 4月21～5月21日
                constellation = day <= 21 ? constellationList.get(3) : constellationList.get(4);
                break;
            case 6:
                //双子座 5月22日～6月21日
                constellation = day <= 21 ? constellationList.get(4) : constellationList.get(5);
                break;
            case 7:
                //Cancer 巨蟹座（6月22日～7月22日）
                constellation = day <= 22 ? constellationList.get(5) : constellationList.get(6);
                break;
            case 8:
                //Leo 狮子座（7月23日～8月23日）
                constellation = day <= 23 ? constellationList.get(6) : constellationList.get(7);
                break;
            case 9:
                //Virgo 处女座（8月24日～9月23日）
                constellation = day <= 23 ? constellationList.get(7) : constellationList.get(8);
                break;
            case 10:
                //Libra 天秤座（9月24日～10月23日）
                constellation = day <= 23 ? constellationList.get(8) : constellationList.get(9);
                break;
            case 11:
                //Scorpio 天蝎座（10月24日～11月22日）
                constellation = day <= 22 ? constellationList.get(9) : constellationList.get(10);
                break;
            case 12:
                //Sagittarius 射手座（11月23日～12月21日）
                constellation = day <= 21 ? constellationList.get(10) : constellationList.get(11);
                break;
        }
        return constellation;
    }

}
