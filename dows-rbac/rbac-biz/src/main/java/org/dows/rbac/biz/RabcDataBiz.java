package org.dows.rbac.biz;

import lombok.RequiredArgsConstructor;
import org.dows.rbac.service.RabcDataService;
import org.springframework.stereotype.Service;

/**
 * RBAC-数据资源(RabcData)业务实现类
 *
 * @author VX:PN15855012581
 * @since 2022-07-14 22:57:01
 */
@RequiredArgsConstructor
@Service
public class RabcDataBiz {
    private final RabcDataService service;

}
