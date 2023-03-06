package org.dows.account.biz.util;

import org.dows.account.dto.AccountGroupInfoDTO;
import org.dows.account.entity.AccountGroupInfo;
import org.dows.account.vo.AccountGroupInfoVo;
import org.dows.framework.api.exceptions.BizException;
import org.springframework.util.StringUtils;

/**
 * 用户组(工具类)
 */
public class AccountGroupInfoUtil {

    public static AccountGroupInfoVo buildVo(AccountGroupInfo entity) {
        return AccountGroupInfoVo.builder()
            .accountId(entity.getAccountId())
            .address(entity.getAddress())
            .deleted(entity.getDeleted())
            .descr(entity.getDescr())
            .district(entity.getDistrict())
            .dt(entity.getDt())
            .email(entity.getEmail())
            .groupInfoId(entity.getGroupInfoId())
            .groupInfoName(entity.getGroupInfoName())
            .id(entity.getGroupInfoId())
            .orgCode(entity.getOrgCode())
            .orgId(entity.getOrgId())
            .orgName(entity.getOrgName())
            .owner(entity.getOwner())
            .ownerPhone(entity.getOwnerPhone())
            .ownerPosition(entity.getOwnerPosition())
            .postal(entity.getPostal())
            .status(entity.getStatus())
            .userId(entity.getUserId())
            .website(entity.getWebsite())
            .build();
    }

    public static AccountGroupInfo buildEntity(AccountGroupInfoDTO dto) {
        return AccountGroupInfo.builder()
            .accountId(dto.getAccountId())
            .address(dto.getAddress())
            .deleted(dto.getDeleted())
            .descr(dto.getDescr())
            .district(dto.getDistrict())
            .dt(dto.getDt())
            .email(dto.getEmail())
            .groupInfoId(dto.getGroupInfoId())
            .groupInfoName(dto.getGroupInfoName())
            .orgCode(dto.getOrgCode())
            .orgId(dto.getOrgId())
            .orgName(dto.getOrgName())
            .owner(dto.getOwner())
            .ownerPhone(dto.getOwnerPhone())
            .ownerPosition(dto.getOwnerPosition())
            .postal(dto.getPostal())
            .status(dto.getStatus())
            .userId(dto.getUserId())
            .website(dto.getWebsite())
            .build();
    }

    /**
     * 验证更新数据的对象(静态验证)
     */
    public static void validateUpdateDTO(AccountGroupInfoDTO dto) throws BizException {
        String groupInfoId = dto.getGroupInfoId();
        if (StringUtils.hasText(groupInfoId)) {
            throw new BizException("更新用户组信息时用户组ID参数必填");
        }
    }

    /**
     * 验证创建数据对象
     */
    public static void validateCreateDTO(AccountGroupInfoDTO dto) throws BizException {
        String accountId = dto.getAccountId();
        if (org.springframework.util.StringUtils.hasText(accountId)) {
            throw new BizException("创建用户组信息时负责人账户ID参数必填");
        }
    }

}
