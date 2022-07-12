package org.dows.account.rest.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.crud.entity.AccountInstance;
import org.dows.account.crud.service.AccountInstanceService;
import org.dows.account.rest.admin.PrincipalRoleRest;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :yangxh
 * @Title: PrincipalRoleController
 * @ProjectName it-cloud
 * @Description: TODO
 * @date 2022/1/1212:08
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class PrincipalRoleController implements PrincipalRoleRest<AccountInstance, AccountInstanceService> {
}
