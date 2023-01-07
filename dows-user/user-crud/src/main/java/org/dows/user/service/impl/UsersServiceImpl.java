package org.dows.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.user.entity.Users;
import org.dows.user.mapper.UsersMapper;
import org.dows.user.service.UsersService;
import org.springframework.stereotype.Service;


/**
 * 用户-实例(Users)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-12-20 11:46:17
 */
@RequiredArgsConstructor
@Service("usersService")
public class UsersServiceImpl extends MybatisCrudServiceImpl<UsersMapper, Users> implements UsersService {

    private final UsersMapper usersMapper;

    @Override
    public boolean ifExist(Users users) {

        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<Users> lambdaQueryWrapper = wrapper.lambda().eq(Users::getUserName, users.getUserName());
        if (users.getId() != null && users.getId() != 0) {
            lambdaQueryWrapper.ne(Users::getId, users.getId());
        }
        return usersMapper.selectCount(wrapper) > 0;
    }
}

