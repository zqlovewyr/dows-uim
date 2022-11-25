package org.dows.account.biz.util;

import org.dows.account.biz.dto.AccountInstanceDTO;
import org.dows.account.biz.dto.TreeAccountOrgDTO;

/**
 * @author runsix
 */
public interface AccountUtil {
    /* runsix:static validate AccountInstanceDTO */
    static void validateAccountInstanceDTO(AccountInstanceDTO accountInstanceDTO) {}

    static void validateAccountOrgDTO(TreeAccountOrgDTO treeAccountOrgDTO){}
}
