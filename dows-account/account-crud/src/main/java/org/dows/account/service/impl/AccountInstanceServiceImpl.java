package org.dows.account.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.dows.account.entity.AccountInstance;
import org.dows.account.mapper.AccountInstanceMapper;
import org.dows.account.query.AccountInstanceQuery;
import org.dows.account.service.AccountInstanceService;
import org.dows.account.vo.AccountInstanceResVo;
import org.dows.account.vo.AccountInstanceVo;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 账号-实例(AccountInstance)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:53
 */
@Service("accountInstanceService")
@RequiredArgsConstructor
public class AccountInstanceServiceImpl extends MybatisCrudServiceImpl<AccountInstanceMapper, AccountInstance> implements AccountInstanceService {

    private final AccountInstanceMapper accountInstanceMapper;
    @Override
    public List<AccountInstanceVo> getAccountInstanceByUserNameAndTenantId(Map<String, Object> param) {
        return accountInstanceMapper.getAccountInstanceByUserNameAndTenantId(param);
    }

    @Override
    public IPage<AccountInstanceResVo> getListByPage(IPage<AccountInstanceResVo> page, AccountInstanceQuery accountInstanceQuery) {
        IPage<AccountInstanceResVo> list = accountInstanceMapper.selectAccountInstancePage(page, accountInstanceQuery);
        list.getRecords().stream().forEach(m ->{
            if(m.getSex() == 1){ // 男
                m.setSexStr("男");
            }else if(m.getSex() == 2){
                m.setSexStr("女");
            }else {
                m.setSexStr("未知");
            }

            if(StringUtils.isNotBlank(m.getIdentifier())){
                m.setAge(getAge(m.getIdentifier())); // 年龄
            }
            if(m.getEntryTime() != null){
                m.setJobHours(gl(DateUtil.format(m.getEntryTime(), "yyyy-MM"),DateUtil.format(new Date(), "yyyy-MM")));
            }

        });
        return list;
    }

    /**
     * 根据身份证获取年龄
     * @param certificateNo
     * @return
     */
    public static Integer getAge(String certificateNo) {
        Integer age = null;
        int year = LocalDate.now().getYear();
        char[] number = certificateNo.toCharArray();
        boolean flag = true;
        if (number.length == 15) {
            for (char c : number) {
                if (!flag)
                    return age;
                flag = Character.isDigit(c);
            }
        } else if (number.length == 18) {
            for (int x = 0; x < number.length - 1; x++) {
                if (!flag)
                    return age;
                flag = Character.isDigit(number[x]);
            }
        }
        if (flag && certificateNo.length() == 15) {
            age = (year - Integer.parseInt("19" + certificateNo.substring(6, 8)));
        } else if (flag && certificateNo.length() == 18) {
            age = (year - Integer.parseInt(certificateNo.substring(6, 10)));
        }
        return age;
    }
    /**
     * 计算工龄  日期格式为yyyy-MM
     * @param t1 入职日期
     * @param t2 当前日期
     * @return XX年XX月
     */
    public static String gl(String t1,String t2) {
        String[] split1 = t1.split("-");
        String[] split2 = t2.split("-");

        int m2 = Integer.parseInt(split2[1]);
        int m1 = Integer.parseInt(split1[1]);
        int month=0;
        int year = 0;
        if(m2 >= m1) {
            month = m2 -m1;
            year = Integer.parseInt(split2[0]) - Integer.parseInt(split1[0]);
        }else {
            month = 12-m1 +m2;
            year = Integer.parseInt(split2[0])- 1 - Integer.parseInt(split1[0]);
        }

        String rest="";
        if(year == 0) {
            rest=month+"月";
        }else if(month == 0) {
            rest=year+"年";
        }else {
            rest = year+"年"+month+"月";
        }
        return rest;
    }

}

