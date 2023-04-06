package org.dows.account.biz;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.api.AccountUserApi;
import org.dows.account.biz.dto.AccountUserDTO;
import org.dows.account.bo.AccountCouponBo;
import org.dows.account.bo.AccountInstanceTenantBo;
import org.dows.account.bo.AccountUserBo;
import org.dows.account.bo.IffSettingBo;
import org.dows.account.entity.AccountInstance;
import org.dows.account.entity.AccountUser;
import org.dows.account.entity.AccountUserInfo;
import org.dows.account.entity.IffSetting;
import org.dows.account.query.AccountCountTenantQuery;
import org.dows.account.query.AccountQuery;
import org.dows.account.service.*;
import org.dows.account.vo.*;
import org.dows.framework.api.Response;
import org.dows.framework.api.exceptions.BaseException;
import org.dows.marketing.api.MarketCouponApi;
import org.dows.marketing.form.MarketCouponForm;
import org.dows.marketing.form.MarketCouponQueryForm;
import org.dows.marketing.form.MarketUserCouponListForm;
import org.dows.marketing.form.SentCouponForm;
import org.dows.marketing.vo.MarketListCouponVo;
import org.dows.marketing.vo.MarketUserListCouponVo;
import org.dows.order.api.OrderInstanceBizApiService;
import org.dows.order.form.OrderTaPageForm;
import org.dows.order.form.OrderTaTypeForm;
import org.dows.order.vo.OrderTaPackVo;
import org.dows.order.vo.OrderTaTableVo;
import org.dows.order.vo.OrderTaTakeOutVo;
import org.dows.order.vo.OrderTaVo;
import org.dows.store.api.response.StoreResponse;
import org.dows.store.biz.StoreInstanceBiz;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
public class AccountBiz implements AccountUserApi {
    @Autowired
    private AccountUserInfoService accountUserInfoService;
    @Autowired
    private AccountInstanceService accountInstanceService;

    @Autowired
    private IffSettingService iffSettingService;
    @Autowired
    @Lazy
    private MarketCouponApi marketCouponApi;

    @Autowired
    private AccountAddressService accountAddressService;
    @Autowired
    private StoreInstanceBiz storeInstanceBiz;
    @Lazy
    @Autowired
    private OrderInstanceBizApiService orderInstanceBizApiService;
    @Autowired
    private AccountUserService accountUserService;

    public Response getAccuntListPage(AccountQuery accountQuery){
        // TODO 消费金额和最后下单时间排序
        // TODO 返回客户消费金额、下单时间、订单量
        Page<AccountVo> page = new Page<>(accountQuery.getPage(), accountQuery.getSize());
        IPage<AccountVo> listByPage = accountUserInfoService.getListByPage(page, accountQuery);
        return Response.ok(listByPage);
    }
    @Override
    public Response<Boolean> updateAccountUser(AccountUserBo accountUserBo){
        AccountUserDTO accountUserDTO = new AccountUserDTO();
        BeanUtil.copyProperties(accountUserBo,accountUserDTO);
        boolean update = accountUserService.lambdaUpdate()
                .set(AccountUser::getUserId, accountUserDTO.getUserId())
                .set(AccountUser::getAppId, accountUserDTO.getAppId())
                .eq(AccountUser::getAccountId, accountUserDTO.getAccountId())
                .update();
        return Response.ok(update);
    }

    @Override
    public Response<AccountUserVo> getAccountUser(AccountUserBo accountUserBo) {
        AccountUserDTO accountUserDTO = new AccountUserDTO();
        BeanUtil.copyProperties(accountUserBo,accountUserDTO);
        AccountUser accountUser = accountUserService.lambdaQuery()
                .eq(!ObjectUtil.isEmpty(accountUserDTO.getAppId()),AccountUser::getAppId, accountUserDTO.getAppId())
                .eq(!ObjectUtil.isEmpty(accountUserDTO.getUserId()),AccountUser::getUserId, accountUserDTO.getUserId())
                .one();
        AccountUserVo accountUserVo = new AccountUserVo();
        BeanUtil.copyProperties(accountUser,accountUserVo);
        return Response.ok(accountUserVo);
    }

    @Override
    public AccountVo getInfoByAccountId(String accountId){
        AccountVo accountVo = new AccountVo();
        AccountInstance accountInstance = accountInstanceService.lambdaQuery()
                .eq(AccountInstance::getAccountId, accountId)
                .one();
        if (accountInstance == null) {
            return null;
        }
        BeanUtil.copyProperties(accountInstance, accountVo);
        accountVo.setCreateTime(accountInstance.getDt());
        AccountUserInfo accountUserInfo = accountUserInfoService.lambdaQuery()
                .eq(AccountUserInfo::getAccountId, accountId)
                .one();
        BeanUtil.copyProperties(accountUserInfo, accountVo);
        return accountVo;
    }

    @Override
    public List<AccountVo> getInfoByAccountIds(List<String> accountIds){
        List<AccountVo> accountVos = new ArrayList<>();
        List<AccountInstance> accountInstance = accountInstanceService.lambdaQuery()
                .in(AccountInstance::getAccountId, accountIds)
                .list();
        if (accountInstance.isEmpty()) {
            return null;
        }
        accountInstance.stream().forEach(item ->{
            AccountVo accountVo = new AccountVo();
            BeanUtil.copyProperties(item, accountVo);
            AccountUserInfo accountUserInfo = accountUserInfoService.lambdaQuery()
                    .eq(AccountUserInfo::getAccountId, accountVo.getAccountId())
                    .one();
            BeanUtil.copyProperties(accountUserInfo, accountVo);
            accountVos.add(accountVo);
        });
        return accountVos;
    }

    @Override
    public AccountVo queryAccountVoByAccountName(String accountName, Integer accountType) {
        if("admin".equals(accountName)){ // admin账户为总控端
            accountType = 1;
        }
        AccountVo accountVo = new AccountVo();
        AccountInstance accountInstance = accountInstanceService.lambdaQuery()
                .eq(AccountInstance::getAccountName, accountName)
                .eq(AccountInstance::getAccountType, accountType)
                .one();
        if (accountInstance == null) {
            throw new BaseException(HttpStatus.BAD_REQUEST.value(), "登录用户：" + accountName + " 不存在");
        }

        BeanUtil.copyProperties(accountInstance, accountVo);
        AccountUserInfo accountUserInfo = accountUserInfoService.lambdaQuery()
                .eq(AccountUserInfo::getAccountId, accountVo.getAccountId())
                .one();
        BeanUtil.copyProperties(accountUserInfo, accountVo);
        return accountVo;
    }

    @Override
    public Map<String, Object> selectAccountIntegralStatistics(String accountId) {
        return null;
    }

    @Override
    public IPage<AccountIntegralVo> selectAccountIntegralPage(String accountId) {
        return null;
    }

    @Override
    public Map<String,Object> selectAccountCouponStatistics(AccountCouponBo accountCouponBo) {
        return null;
    }

    @Override
    public IPage<AccountCouponVo> selectAccountCouponPage(AccountCouponBo accountCouponBo) {

        return null;
    }

    @Override
    public List<AccountCouponVo> selectStoreCouponList(AccountCouponBo accountCouponBo) {

        MarketCouponQueryForm marketCouponQueryForm = new MarketCouponQueryForm();
        marketCouponQueryForm.setStoreId(Long.valueOf(accountCouponBo.getStoreId()));
        marketCouponQueryForm.setCategoryCode(12001);
        marketCouponQueryForm.setSize(1000);
        IPage<MarketListCouponVo> page = marketCouponApi.getCouponList(marketCouponQueryForm);
        List<AccountCouponVo> list = new ArrayList<>();
        page.getRecords().stream().forEach(item ->{
            AccountCouponVo accountCouponVo = new AccountCouponVo();
            accountCouponVo.setCouponName(item.getMarketName());
          //   accountCouponVo.setCouponId(item.get); TODO 优惠券ID
            list.add(accountCouponVo);
        });
        return list;
    }

    @Override
    public Boolean addOrUpdateCoupon(MarketCouponForm couponForm) {
        return marketCouponApi.addOrUpdateCoupon(couponForm);
    }

    @Override
    public Boolean senCoupon(SentCouponForm sentCoupon) {
        return marketCouponApi.senCoupon(sentCoupon);
    }

    @Override
    public IPage<MarketListCouponVo> getCouponList(MarketCouponQueryForm queryForm) {
        return marketCouponApi.getCouponList(queryForm);
    }


    @Override
    public OrderTaVo selectOrderStatistics(OrderTaTypeForm typeForm) {
        return orderInstanceBizApiService.getTaOrderStat(typeForm);
    }

    @Override
    public List<OrderTaTableVo> getTaOrderTablePage(OrderTaPageForm pageForm) {
        return orderInstanceBizApiService.getTaOrderTablePage(pageForm);
    }

    @Override
    public List<OrderTaPackVo> getTaOrderPackPage(OrderTaPageForm pageForm) {
        return orderInstanceBizApiService.getTaOrderPackPage(pageForm);
    }

    @Override
    public List<OrderTaTakeOutVo> getTaOrderTakeOutPage(OrderTaPageForm pageForm) {
        return orderInstanceBizApiService.getTaOrderTakeOutPage(pageForm);
    }


    @Override
    public Map<String, Object> selectAccountTenantStatistics(AccountInstanceTenantBo accountInstanceTenantBo) {
        AccountCountTenantQuery accountCountTenantQuery = new AccountCountTenantQuery();
        BeanUtils.copyProperties(accountInstanceTenantBo,accountCountTenantQuery);
        return accountInstanceService.selectAccountTenantStatistics(accountCountTenantQuery);
    }

    @Override
    public List<AccountDistributionVo> selectAccountDistributionTenantStatistics(AccountInstanceTenantBo accountInstanceTenantBo) {
        return accountInstanceService.selectAccountDistributionTenantStatistics(accountInstanceTenantBo);
    }

    @Override
    public List<AccountConsumptionVo> selectAccountConsumptionTenantStatistics(AccountInstanceTenantBo accountInstanceTenantBo) {
        return accountInstanceService.selectAccountConsumptionTenantStatistics(accountInstanceTenantBo);
    }

    @Override
    public List<IffSettingVo> selectIffSettingList(IffSettingBo iffSettingBo,Integer ruleNum) {
        List<IffSettingVo> vos = new ArrayList<>();
        List<IffSetting> iffSettings = iffSettingService.lambdaQuery()
                .select(IffSetting::getId,IffSetting::getContent,IffSetting::getTitle)
                .eq(IffSetting::getStoreId,iffSettingBo.getStoreId())
                .eq(IffSetting::getRuleNum,ruleNum) // 规则编号
                .list();
        iffSettings.stream().forEach(item ->{
            IffSettingVo vo = new IffSettingVo();
            BeanUtil.copyProperties(item,vo);
            vos.add(vo);
        });
        return vos;
    }

    @Override
    @Transactional
    public Boolean saveIffSettingList(List<IffSettingBo> iffSettingBos,String storeId,Integer ruleNum) {

        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("store_id",storeId);
        columnMap.put("rule_num",ruleNum);
        iffSettingService.removeByMap(columnMap);
        List<IffSetting> iffSettings = new ArrayList<>();
        iffSettingBos.stream().forEach(item ->{
            IffSetting setting = new IffSetting();
            BeanUtil.copyProperties(item,setting);
            setting.setId(IdWorker.getIdStr());
            setting.setCreateTime(new Date());
            setting.setRuleNum(ruleNum);
            iffSettings.add(setting);
        });
        return iffSettingService.saveBatch(iffSettings);
    }

    @Override
    public StoreResVo getStoreById(String storeId) {
        StoreResVo resVo = new StoreResVo();
        StoreResponse response =storeInstanceBiz.getStoreById(storeId);
        BeanUtils.copyProperties(response,resVo);
        return resVo;
    }

    public UserAssetPointsAndCouponVo selectAssetPointsAndCoupon(String storeId) {
        UserAssetPointsAndCouponVo vo = new UserAssetPointsAndCouponVo();
        MarketUserCouponListForm form = new MarketUserCouponListForm();
        form.setSize(Integer.MAX_VALUE);
        form.setUseStatus(0);
        IPage<MarketUserListCouponVo> page = marketCouponApi.getUserCouponList(form);
        int couponCount = (int )page.getRecords().stream().filter(item -> item.getStoreIds().contains(Long.valueOf(storeId))).count();
        vo.setCouponCount(couponCount);
        return vo;
    }
}
