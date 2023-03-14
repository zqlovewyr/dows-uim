package org.dows.account.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.dows.account.bo.AccountInstanceTenantBo;
import org.dows.account.entity.AccountInstance;
import org.dows.account.entity.IffSetting;
import org.dows.account.mapper.AccountInstanceMapper;
import org.dows.account.query.AccountCountTenantQuery;
import org.dows.account.query.AccountInstanceQuery;
import org.dows.account.service.AccountInstanceService;
import org.dows.account.service.IffSettingService;
import org.dows.account.vo.AccountConsumptionVo;
import org.dows.account.vo.AccountDistributionVo;
import org.dows.account.vo.AccountInstanceResVo;
import org.dows.account.vo.AccountInstanceVo;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;


/**
 * 账号-实例(AccountInstance)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:53
 */
@Service("accountInstanceService")
@RequiredArgsConstructor
public class AccountInstanceServiceImpl extends MybatisCrudServiceImpl<AccountInstanceMapper, AccountInstance> implements AccountInstanceService {

    private final IffSettingService iffSettingService;
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

    @Override
    public Map<String, Object> selectAccountTenantStatistics(AccountCountTenantQuery accountCountTenantQuery) {

        Integer instanceCount =  accountInstanceMapper.selectAccountInstanceCount(accountCountTenantQuery);
        Integer consumeCount = accountInstanceMapper.selectAccountInstanceConsumeCount(accountCountTenantQuery);
        Integer storedCount = accountInstanceMapper.selectAccountInstanceStoredCount(accountCountTenantQuery);

        // 查詢全部客戶
        accountCountTenantQuery.setStartDate(null);
        accountCountTenantQuery.setEndDate(null);

        accountCountTenantQuery.setStoreName(null);
       //  accountCountTenantQuery.setStoreId(null);
        accountCountTenantQuery.setDistrict(null);
        accountCountTenantQuery.setStoreBrand(null);
        accountCountTenantQuery.setStoreType(null);
        accountCountTenantQuery.setStorePattern(null);
        accountCountTenantQuery.setOrderType(null);
        Integer instanceCountAll =  accountInstanceMapper.selectAccountInstanceCount(accountCountTenantQuery);

        Map<String,Object> param = new HashMap<>();
        param.put("instanceCountAll",instanceCountAll !=null ? instanceCountAll : 0);
        param.put("instanceCount",instanceCount !=null ? instanceCount:0);
        param.put("consumeCount",consumeCount !=null ? consumeCount:0);
        param.put("storedCount",storedCount !=null ? storedCount:0);
        return param;
    }

    @Override
    public List<AccountDistributionVo> selectAccountDistributionTenantStatistics(AccountInstanceTenantBo accountInstanceTenantBo) {

        Integer instanceCount =  accountInstanceMapper.selectAccountInstanceCount(AccountCountTenantQuery.builder()
                .startDate(accountInstanceTenantBo.getStartDate())
                .endDate(accountInstanceTenantBo.getEndDate())
                .storeId(accountInstanceTenantBo.getStoreId()).build());
        List<AccountDistributionVo> vos =  accountInstanceMapper.selectAccountDistributionTenantStatistics(accountInstanceTenantBo);
        vos.stream().forEach(item ->{
            if(instanceCount != null && instanceCount != 0){
                // 计算占比
                item.setRate(new BigDecimal((float)item.getCount()/instanceCount).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue());
            }else{
                item.setRate(0);
            }
        });

        return vos;
    }

    @Override
    public List<AccountConsumptionVo> selectAccountConsumptionTenantStatistics(AccountInstanceTenantBo accountInstanceTenantBo) {
        List<AccountConsumptionVo> vos = new ArrayList<>();
        if(accountInstanceTenantBo.getConsumptionType() == 1){ // 消费频次分析
            accountInstanceTenantBo.setFrequencyType(1);
            List<AccountConsumptionVo> vo = accountInstanceMapper.selectAccountConsumptionTenantStatisticsFrequency(accountInstanceTenantBo);
            vos.addAll(vo);
            accountInstanceTenantBo.setFrequencyType(2);
            vos.addAll(accountInstanceMapper.selectAccountConsumptionTenantStatisticsFrequency(accountInstanceTenantBo));
            accountInstanceTenantBo.setFrequencyType(3);
            vos.addAll(accountInstanceMapper.selectAccountConsumptionTenantStatisticsFrequency(accountInstanceTenantBo));
            accountInstanceTenantBo.setFrequencyType(4);
            vos.addAll(accountInstanceMapper.selectAccountConsumptionTenantStatisticsFrequency(accountInstanceTenantBo));
        }

        if(accountInstanceTenantBo.getConsumptionType() == 2){ // 消费能力分析

            List<IffSetting> list = iffSettingService.lambdaQuery()
                    .select(IffSetting::getContent,IffSetting::getTitle)
                    .eq(IffSetting::getStoreId,accountInstanceTenantBo.getStoreId())
                    .eq(IffSetting::getRuleNum,100001).list();
            if(CollectionUtil.isEmpty(list)){
                return new ArrayList<>();
            }
            list.stream().forEach(item ->{
                accountInstanceTenantBo.setFrequencyStartCount(Integer.valueOf(item.getContent().split(",")[0]));
                accountInstanceTenantBo.setFrequencyEndCount(Integer.valueOf(item.getContent().split(",")[1]));
                AccountConsumptionVo vo = accountInstanceMapper.selectAccountConsumptionTenantStatisticsCapacity(accountInstanceTenantBo);
                vo.setCount(item.getTitle());
                vos.add(vo);
            });

        }
        if(accountInstanceTenantBo.getConsumptionType() == 3){ // 客户流失分析

            List<IffSetting> list = iffSettingService.lambdaQuery()
                    .select(IffSetting::getContent,IffSetting::getTitle)
                    .eq(IffSetting::getStoreId,accountInstanceTenantBo.getStoreId())
                    .eq(IffSetting::getRuleNum,100002).list();

            list.stream().forEach(item ->{
                accountInstanceTenantBo.setDistributionCount(Integer.valueOf(item.getContent()));
                AccountConsumptionVo vo = accountInstanceMapper.selectAccountConsumptionTenantStatisticsCustomer(accountInstanceTenantBo);
                vo.setCount(item.getTitle());
                vos.add(vo);
            });
        }
        Integer instanceCount =  accountInstanceMapper.selectAccountInstanceCount(AccountCountTenantQuery.builder()
                .startDate(accountInstanceTenantBo.getStartDate())
                .endDate(accountInstanceTenantBo.getEndDate())
                .storeId(accountInstanceTenantBo.getStoreId()).build());
        vos.stream().forEach(item ->{
            if(instanceCount != null && instanceCount != 0){
                // 计算占比
                item.setRate(new BigDecimal((float)item.getPreCount()/instanceCount).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).intValue());
            }else{
                item.setRate(0);
            }
        });

        return vos;
    }

    @Override
    public List<String> queryAccountIdList(String keyword) {
        return null;
    }
}

