package org.dows.account.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dows.account.bo.AccountInstanceTenantBo;
import org.dows.account.query.AccountCountTenantQuery;
import org.dows.account.query.AccountInstanceQuery;
import org.dows.account.query.AccountQuery;
import org.dows.account.vo.*;
import org.dows.framework.crud.mybatis.MybatisCrudMapper;
import org.dows.account.entity.AccountInstance;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 账号-实例(AccountInstance)表数据库访问层
 *
 * @author lait.zhang
 * @since 2022-11-24 14:23:53
 */
@Mapper
public interface AccountInstanceMapper extends MybatisCrudMapper<AccountInstance> {

    List<AccountInstanceVo> getAccountInstanceByUserNameAndTenantId(@Param("paramQuery")Map<String,Object> param);

    IPage<AccountInstanceResVo> selectAccountInstancePage(IPage<AccountInstanceResVo> page, @Param("accountInstanceQuery") AccountInstanceQuery accountInstanceQuery);

    /**
     * 统计用户数量
     * @param accountCountTenantQuery
     * @return
     */
    Integer selectAccountInstanceCount(@Param("accountCountTenantQuery") AccountCountTenantQuery accountCountTenantQuery);

    /**
     * 统计消费客户用户数量
     * @param accountCountTenantQuery
     * @return
     */
    Integer selectAccountInstanceConsumeCount(@Param("accountCountTenantQuery") AccountCountTenantQuery accountCountTenantQuery);

    /**
     * 统计消费客户用户数量
     * @param accountCountTenantQuery
     * @return
     */
    Integer selectAccountInstanceStoredCount(@Param("accountCountTenantQuery") AccountCountTenantQuery accountCountTenantQuery);

    /**
     * 客户分布
     * @param accountInstanceTenantBo
     * @return
     */
    List<AccountDistributionVo> selectAccountDistributionTenantStatistics(@Param("accountInstanceTenantBo")AccountInstanceTenantBo accountInstanceTenantBo);

    /**
     * 消费频次分析
     * @param accountInstanceTenantBo
     * @return
     */
    List<AccountConsumptionVo> selectAccountConsumptionTenantStatisticsFrequency(@Param("accountInstanceTenantBo")AccountInstanceTenantBo accountInstanceTenantBo);

    /**
     * 消费能力分析
     * @param accountInstanceTenantBo
     * @return
     */
    List<AccountConsumptionVo> selectAccountConsumptionTenantStatisticsCapacity(@Param("accountInstanceTenantBo")AccountInstanceTenantBo accountInstanceTenantBo);
    /**
     * 客户流失分析
     * @param accountInstanceTenantBo
     * @return
     */
    List<AccountConsumptionVo> selectAccountConsumptionTenantStatisticsCustomer(@Param("accountInstanceTenantBo")AccountInstanceTenantBo accountInstanceTenantBo);

}

