package org.dows.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dows.rbac.entity.RbacResource;
import org.dows.rbac.mapper.RbacResourceMapper;
import org.dows.rbac.service.RbacResourceService;
import org.springframework.stereotype.Service;

/**
 * RBAC-应用资源(RbacResource)表服务实现类
 *
 * @author VX:PN15855012581
 * @since 2021-09-15 12:35:21
 */
@Service
public class RbacResourceServiceImpl extends ServiceImpl<RbacResourceMapper, RbacResource> implements RbacResourceService {

}
