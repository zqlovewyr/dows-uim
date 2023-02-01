package org.dows.user.biz;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.Response;
import org.dows.user.api.api.UserContactApi;
import org.dows.user.api.dto.UserContactDTO;
import org.dows.user.api.vo.UserContactVo;
import org.dows.user.entity.UserContact;
import org.dows.user.service.UserContactService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author：jiangxia
 * @Date：2023/01/10 13:26
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class UserContactBiz implements UserContactApi {

    private final UserContactService userContactService;

    @Override
    public Response<List<UserContactVo>> getUserContactList(UserContactDTO userContactDTO) {
        List<UserContact> userContactList = userContactService.lambdaQuery()
                .like(StringUtils.isNotEmpty(userContactDTO.getUserId()), UserContact::getUserId, userContactDTO.getUserId())
                .like(StringUtils.isNotEmpty(userContactDTO.getContact()),UserContact::getContact, userContactDTO.getContact())
                .like(StringUtils.isNotEmpty(userContactDTO.getContactNum()),UserContact::getContactNum, userContactDTO.getContactNum())
                .eq(userContactDTO.getContactType() != null,UserContact::getContactType,userContactDTO.getContactType())
                .eq(userContactDTO.getStatus() != null,UserContact::getStatus,userContactDTO.getStatus())
                .eq(userContactDTO.getSelf() != null,UserContact::getSelf,userContactDTO.getSelf())
                .eq(userContactDTO.getDt() != null, UserContact::getDt, userContactDTO.getDt())
                .gt(userContactDTO.getStartTime() != null, UserContact::getDt, userContactDTO.getStartTime())
                .lt(userContactDTO.getEndTime() != null, UserContact::getDt, userContactDTO.getEndTime())
                .list();
        //复制属性
        List<UserContactVo> voList = new ArrayList<>();
        userContactList.forEach(user->{
            UserContactVo vo = new UserContactVo();
            BeanUtils.copyProperties(user,vo);
            voList.add(vo);
        });
        return Response.ok(voList);
    }
}
