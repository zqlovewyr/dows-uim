package org.dows.user.service;

import org.dows.framework.crud.mybatis.MybatisCrudService;
import org.dows.user.entity.Users;


/**
 * 用户(Users)表服务接口
 *
 * @author lait.zhang
 * @since 2022-12-20 11:46:17
 */
public interface UsersService extends MybatisCrudService<Users> {

    /**
     * 验证该用户是否存在
     * @param users
     * @return boolean
     */
    boolean ifExist(Users users);

}

