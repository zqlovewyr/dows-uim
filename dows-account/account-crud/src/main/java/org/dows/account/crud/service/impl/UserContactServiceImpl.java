package org.dows.account.crud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dows.account.crud.entity.UserContact;
import org.dows.account.crud.mapper.UserContactMapper;
import org.dows.account.crud.service.UserContactService;
import org.springframework.stereotype.Service;

/**
 * @author :yangxh
 * @Title: UserContactServiceImpl
 * @ProjectName it-cop
 * @Description: TODO
 * @date 2022/4/718:27
 */
@Service
public class UserContactServiceImpl extends ServiceImpl<UserContactMapper, UserContact> implements UserContactService {
}
