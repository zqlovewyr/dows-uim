package org.dows.user.biz;


import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.user.entity.Users;
import org.dows.user.form.UsersForm;
import org.dows.user.service.UsersService;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class UsersBiz {

    private final UsersService usersService;

    /**
     * 保存基础用户信息
     */
    public void saveUser(UsersForm usersForm) throws Exception {

        Users users = BeanUtil.copyProperties(usersForm, Users.class);
        if (users == null) {
            return;
        }
        // 修改：根据用户名校验 -> 是否存在本身以外的相同的名称
        // 新增：根据用户名名称，校验是否已存在
        if (usersService.ifExist(users)) {
            throw new RuntimeException("该账号已存在！");
        }
        if (users.getId() != null && users.getId() != 0) {
            usersService.updateById(users);
        } else {
            usersService.save(users);
        }
    }
}
