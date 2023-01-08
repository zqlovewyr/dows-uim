package org.dows.account.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dows.account.query.AccountQuery;
import org.dows.account.vo.AccountVo;

@Mapper
public interface AccountMapper {
    IPage<AccountVo> selectAccountPage(IPage<AccountVo> page, @Param("accountQuery") AccountQuery accountQuery);
}
