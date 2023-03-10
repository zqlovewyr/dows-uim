package org.dows.account.biz.util;

import org.dows.account.dto.AccountInstanceDTO;
import org.dows.account.entity.AccountInstance;
import org.dows.account.vo.AccountInstanceVo;

/**
 * 账户实例对象的工具类
 */
public class AccountInstanceUtil {
    public static AccountInstanceVo buildVo(AccountInstance entity) {
        if (null == entity) {
            return new AccountInstanceVo();
        }
        return AccountInstanceVo.builder()
            .accountId(Long.parseLong(entity.getAccountId()))
            .accountName(entity.getAccountName())
            .avatar(entity.getAvatar())
            .id(entity.getId().toString())
            .password(entity.getPassword())
            .phone(entity.getPhone())
            .source(entity.getSource())
            .status(entity.getStatus())
            .dt(entity.getDt())
            .build();
    }

    public static AccountInstance buildEntity(AccountInstanceDTO dto) {
        return AccountInstance.builder()
            .accountId(dto.getAccountId())
            .accountName(dto.getAccountName())
            .avatar(dto.getAvatar())
            .id(dto.getId() == null ? null : Long.valueOf(dto.getId()))
            .password(dto.getPassword())
            .phone(dto.getPhone())
            .source(dto.getSource())
            .status(dto.getStatus())
            .build();
    }
}
