package org.dows.account.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.dows.account.query.AccountInstanceQuery;
import org.dows.account.vo.AccountInstanceResVo;

/**
 * 账号-实例维度信息(AccountInstance)Api接口
 *
 * @author VX:PN15855012581
 * @since 2021-09-10 15:41:25
 */
public interface AccountInstanceApi {

    /**
     * 分页查询
     * @param query
     * @return
     */
    IPage<AccountInstanceResVo> getAccountInstanceListPage(AccountInstanceQuery query);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    AccountInstanceResVo getAccountInstanceInfo(Long id);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    Boolean deleteById(Long id);

}
