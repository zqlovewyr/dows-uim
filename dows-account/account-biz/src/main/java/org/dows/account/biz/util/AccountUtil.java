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
}
