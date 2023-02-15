package org.dows.account.biz.util;

import org.apache.commons.lang3.StringUtils;
import org.dows.account.biz.constant.AccountInstanceConstant;
import org.dows.account.biz.dto.AccountInstanceDTO;
import org.dows.account.biz.enums.EnumAccountStatusCode;
import org.dows.account.biz.exception.AccountException;

import java.util.Objects;
import org.dows.account.biz.dto.AccountOrgDTO;
import org.dows.account.biz.dto.AccountOrgGroupDTO;
import org.dows.account.biz.dto.TreeAccountOrgDTO;
import org.dows.account.biz.dto.TreeAccountOrgDTO;

/**
 * @author runsix
 */
public interface AccountUtil {

    final static int[] dayArr = new int[] { 20, 19, 21, 20, 21, 22, 23,
            23, 23, 24, 23, 22 };
    final static String[] constellationArr = new String[] { "摩羯座",
            "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座",
            "天蝎座", "射手座", "摩羯座" };

    /* runsix:static validate AccountInstanceDTO */
    static AccountInstanceDTO validateAndTrimAccountInstanceDTO(AccountInstanceDTO accountInstanceDTO) {
        if (Objects.isNull(accountInstanceDTO)) {
            throw new AccountException(EnumAccountStatusCode.ACCOUNT_INSTANCE_DTO_CANNOT_BE_NULL);
        }
        if (StringUtils.isBlank(accountInstanceDTO.getAppId())) {
            throw new AccountException(EnumAccountStatusCode.APPID_CANNOT_BE_BLANK);
        } else {
            accountInstanceDTO.setAppId(accountInstanceDTO.getAppId().trim());
        }
        if (StringUtils.isBlank(accountInstanceDTO.getIdentifier())) {
            throw new AccountException(EnumAccountStatusCode.IDENTIFIER_CANNOT_BE_BLANK);
        } else {
            accountInstanceDTO.setIdentifier(accountInstanceDTO.getIdentifier().trim());
        }
        if (StringUtils.isBlank(accountInstanceDTO.getAccountName())) {
            throw new AccountException(EnumAccountStatusCode.ACCOUNT_NAME_CANNOT_BE_BLANK);
        } else {
            accountInstanceDTO.setAccountName(accountInstanceDTO.getAccountName().trim());
        }
        if (StringUtils.isBlank(accountInstanceDTO.getPassword())) {
            accountInstanceDTO.setPassword(AccountInstanceConstant.DEFAULT_PASSWORD);
        }
        if (StringUtils.isNotBlank(accountInstanceDTO.getAvatar())) {
            accountInstanceDTO.setAvatar(accountInstanceDTO.getAvatar().trim());
        }
        if (StringUtils.isNotBlank(accountInstanceDTO.getSource())) {
            accountInstanceDTO.setSource(accountInstanceDTO.getSource().trim());
        }
        if (StringUtils.isNotBlank(accountInstanceDTO.getPhone())) {
            accountInstanceDTO.setPhone(accountInstanceDTO.getPhone().trim());
        }
        return accountInstanceDTO;
    }

    static boolean excelValidateAccountInstanceDTO(AccountInstanceDTO accountInstanceDTO) {
        if (Objects.nonNull(accountInstanceDTO)) {
            if (StringUtils.isNotBlank(accountInstanceDTO.getIdentifier())) {
                if (StringUtils.isNotBlank(accountInstanceDTO.getAccountName())) {
                    return true;
                } else {
                    throw new AccountException(String.format("Excel中账号：'%s'的'%s'", accountInstanceDTO.getIdentifier(), EnumAccountStatusCode.ACCOUNT_NAME_CANNOT_BE_BLANK.getDescr()));
                }
            } else {
                if (StringUtils.isNotBlank(accountInstanceDTO.getAccountName())) {
                    throw new AccountException(String.format("Excel中账号昵称：'%s'的'%s'", accountInstanceDTO.getAccountName(), EnumAccountStatusCode.IDENTIFIER_CANNOT_BE_BLANK.getDescr()));
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    static String getKeyOfkIdentifierAppIdV(String identifier, String appId) {
        return identifier+"#"+appId;
    }

    static void validateAccountInstanceDTO(AccountInstanceDTO accountInstanceDTO) {}

    static void validateAccountOrgDTO(TreeAccountOrgDTO treeAccountOrgDTO){}

    static void validateAccountOrgDTO(AccountOrgDTO orgDTO) {
    }

    static void validateAccountGroupDTO(AccountOrgGroupDTO groupDTO) {
    }



    /**
     * 根据出生日期计算属相和星座
     *
     * @param args
     */
    public static void main(String[] args) {
        int month = 7;
        int day = 28;
        System.out.println("星座为：" + getConstellation(month, day));
        System.out.println("属相为:" + getYear(1994));

    }

    /**
     * 获取几零后
     * @param year
     * @return
     */
    public static String getChronological(int year) {
        String str = String.valueOf(year).substring(2,3);
        if(year < 2000){
            Integer integer= Integer.getInteger(str);
            if(integer>50&&integer<60){
                return "50后";
            }
            if(integer>60&&integer<70){
                return "60后";
            }
            if(integer>70&&integer<80){
                return "70后";
            }
            if(integer>80&&integer<90){
                return "80后";
            }
            if(integer>90&&integer<100){
                return "90后";
            }
        }else {
            Integer integer= Integer.getInteger(str);
            if(integer==0||(integer>1 && integer<10)){
                return "00后";
            }
            if(integer>10&&integer<20){
                return "10后";
            }
        }
        return "未知";
    }
    /**
     * Java通过生日计算星座
     *
     * @param month
     * @param day
     * @return
     */
    public static String getConstellation(int month, int day) {
        return day < dayArr[month - 1] ? constellationArr[month - 1]
                : constellationArr[month];
    }

    /**
     * 通过生日计算属相
     *
     * @param year
     * @return
     */
    public static String getYear(int year) {
        if (year < 1900) {
            return "未知";
        }
        int start = 1900;
        String[] years = new String[] { "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊",
                "猴", "鸡", "狗", "猪" };
        return years[(year - start) % years.length];
    }
}
