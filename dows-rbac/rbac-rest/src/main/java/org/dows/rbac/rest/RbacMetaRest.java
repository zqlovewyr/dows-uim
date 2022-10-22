package org.dows.rbac.rest;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.rbac.entity.RbacMeta;
import org.dows.rbac.form.RbacMetaForm;
import org.dows.rbac.service.RbacMetaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * rbac-元数据(RbacMeta)表控制层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:43:17
 */
@Api(tags = "rbac-元数据")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("rbacMeta")
public class RbacMetaRest implements MybatisCrudRest<RbacMetaForm, RbacMeta, RbacMetaService> {

    //private final RbacMetaBiz rbacMetaBiz;

}

