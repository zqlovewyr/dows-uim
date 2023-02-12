package org.dows.account.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dows.account.bo.AccountCouponBo;
import org.dows.account.bo.AccountInstanceTenantBo;
import org.dows.account.bo.AccountOrderBo;
import org.dows.account.vo.*;

import java.util.List;
import java.util.Map;

/**
 * 账号-用户维度信息(AccountUser)Api接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:36
 */
public interface AccountUserApi {

    /**
     * 根据accountId查询
     * @param accountId
     * @return
     */
    AccountVo getInfoByAccountId(String accountId);

    /**
     * 根据多个accountId查询
     * @param accountIds
     * @return
     */
    List<AccountVo> getInfoByAccountIds(String[] accountIds);

    /**
     * 根据账号名称及类型查询
     * @param accountName
     * @param accountType
     * @return
     */
    AccountVo queryAccountVoByAccountName(String accountName,Integer accountType);

    /**
     * 用户积分列表统计
     * @param accountId
     * @return
     */
    Map<String,Object> selectAccountIntegralStatistics(String accountId);
    /**
     * 用户积分列表
     * @param accountId
     * @return
     */
    IPage<AccountIntegralVo> selectAccountIntegralPage(String accountId);

    /**
     * 优惠券列表页统计
     * @param accountCouponBo
     * @return
     */
    Map<String,Object> selectAccountCouponStatistics(AccountCouponBo accountCouponBo);
    /**
     * 优惠券列表
     * @param accountCouponBo
     * @return
     */
    IPage<AccountCouponVo> selectAccountCouponPage(AccountCouponBo accountCouponBo);

    /**
     * 他的订单统计
     * @param accountOrderBo
     * @return
     */
    AccountOrderStatisticsVo selectOrderStatistics(AccountOrderBo accountOrderBo);

    /**
     * 我的订单分页列表
     * @param accountOrderBo
     * @return
     */
    IPage<AccountOrderVo> selectOrderInstancePage(AccountOrderBo accountOrderBo);

    /**
     * 租户用户统计
     * @param accountInstanceTenantBo
     * @return
     */
    Map<String,Object> selectAccountTenantStatistics(AccountInstanceTenantBo accountInstanceTenantBo);

    /**
     * 租户客户分布统计
     * @param accountInstanceTenantBo
     * @return
     */
    List<AccountDistributionVo> selectAccountDistributionTenantStatistics(AccountInstanceTenantBo accountInstanceTenantBo);

    /**
     * 租户消费能力分析统计
     * @param accountInstanceTenantBo
     * @return
     */
    List<AccountConsumptionVo> selectAccountConsumptionTenantStatistics(AccountInstanceTenantBo accountInstanceTenantBo);

}
