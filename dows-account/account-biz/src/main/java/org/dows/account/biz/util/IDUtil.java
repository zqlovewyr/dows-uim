package org.dows.account.biz.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * @author Administrator
 * @date 2023/1/11 15:36
 */
public class IDUtil {
    private static final long DATACENTERID = 1L;

    public IDUtil() {
    }

    public static long getId(long workerId) {
        Snowflake snowFlak = IdUtil.getSnowflake(workerId, 1L);
        return snowFlak.nextId();
    }
}
