package org.dows.rbac.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.rbac.mapper.RbacUriMapper;
import org.dows.rbac.entity.RbacUri;
import org.dows.rbac.service.RbacUriService;
import org.springframework.stereotype.Service;


/**
 * rbac-url(RbacUri)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-11-24 11:49:49
 */
@DS("uim")
@Service("rbacUriService")
public class RbacUriServiceImpl extends MybatisCrudServiceImpl<RbacUriMapper, RbacUri> implements RbacUriService {

}

