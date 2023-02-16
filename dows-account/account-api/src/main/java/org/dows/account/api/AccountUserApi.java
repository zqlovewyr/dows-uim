package org.dows.account.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dows.account.bo.AccountCouponBo;
import org.dows.account.bo.AccountInstanceTenantBo;
import org.dows.account.bo.AccountOrderBo;
import org.dows.account.bo.IffSettingBo;
import org.dows.account.vo.*;
import org.dows.order.form.OrderTaPageForm;
import org.dows.order.form.OrderTaTypeForm;
import org.dows.order.vo.OrderTaPackVo;
import org.dows.order.vo.OrderTaTableVo;
import org.dows.order.vo.OrderTaTakeOutVo;
import org.dows.order.vo.OrderTaVo;

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
     * 用户优惠券列表页统计
     * @param accountCouponBo
     * @return
     */
    Map<String,Object> selectAccountCouponStatistics(AccountCouponBo accountCouponBo);
    /**
     * 用户优惠券列表
     * @param accountCouponBo
     * @return
     */
    IPage<AccountCouponVo> selectAccountCouponPage(AccountCouponBo accountCouponBo);

    /**
     * 门店优惠券列表
     * @param accountCouponBo
     * @return
     */
    List<AccountCouponVo> selectStoreCouponList(AccountCouponBo accountCouponBo);

    /**
     * 他的订单统计
     * @param typeForm
     * @return
     */
    OrderTaVo selectOrderStatistics(OrderTaTypeForm typeForm);

    /**
     * Ta订单 堂食列表
     * @return
     */
    List<OrderTaTableVo> getTaOrderTablePage(OrderTaPageForm pageForm);

    /**
     * Ta订单 打包列表
     * @return
     */
    List<OrderTaPackVo> getTaOrderPackPage(OrderTaPageForm pageForm);

    /**
     * Ta订单 外卖列表
     * @return
     */
    List<OrderTaTakeOutVo> getTaOrderTakeOutPage(OrderTaPageForm pageForm);

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

    /**
     * 查询规则配置
     * @param iffSettingBo
     * @return
     */
    List<IffSettingVo> selectIffSettingList(IffSettingBo iffSettingBo,Integer ruleNum);

    /**
     * 批量新增规则配置
     * @param iffSettingBos
     * @return
     */
    Boolean saveIffSettingList(List<IffSettingBo> iffSettingBos,String storeId,Integer ruleNum);

}
