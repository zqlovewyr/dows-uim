package org.dows.user.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.user.mapper.UtaContactMapper;
import org.dows.user.entity.UtaContact;
import org.dows.user.service.UtaContactService;
import org.springframework.stereotype.Service;


/**
 * 联系需求发布者(UtaContact)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-10-22 10:31:13
 */
@DS("uim")
@Service("utaContactService")
public class UtaContactServiceImpl extends MybatisCrudServiceImpl<UtaContactMapper, UtaContact> implements UtaContactService {

}

