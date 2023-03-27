package org.dows.user.biz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.Response;
import org.dows.user.api.api.UserContactApi;
import org.dows.user.api.dto.UserContactDTO;
import org.dows.user.api.vo.UserContactVo;
import org.dows.user.api.vo.UserJobVo;
import org.dows.user.entity.UserAddress;
import org.dows.user.entity.UserCompany;
import org.dows.user.entity.UserContact;
import org.dows.user.entity.UserJob;
import org.dows.user.enums.EnumUserStatusCode;
import org.dows.user.exception.UserException;
import org.dows.user.service.UserContactService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：jiangxia
 * @Date：2023/01/10 13:26
 */
@RequiredArgsConstructor
@Slf4j
@Service
@DS("uim")
public class UserContactBiz implements UserContactApi {

    private final UserContactService userContactService;

    @Override
    public Response<String> insertUserContact(UserContactDTO userContactDTO) {
        UserContact userContact = new UserContact();
        BeanUtils.copyProperties(userContactDTO, userContact);
        boolean userFlag = userContactService.save(userContact);
        if (userFlag == false) {
            throw new UserException(EnumUserStatusCode.USER_CONTACT_CREATE_FAIL_EXCEPTION);
        }
        return Response.ok(userContact.getId().toString());
    }

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

    @Override
    public Response<UserContactVo> getUserContactByUserId(String userId) {
        LambdaQueryWrapper<UserContact> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserContact::getUserId, userId);
        UserContact userContact = userContactService.getOne(queryWrapper);
        //复制属性
        UserContactVo vo = new UserContactVo();
        if (userContact != null) {
            BeanUtils.copyProperties(userContact, vo);
            vo.setId(userContact.getId().toString());
        }
        return Response.ok(vo);
    }

    @Override
    public Response<String> updateUserContactById(UserContactDTO userContactDTO) {
        UserContact userContact = new UserContact();
        BeanUtils.copyProperties(userContactDTO, userContact);
        userContact.setId(Long.valueOf(userContactDTO.getId()));
        boolean userFlag = userContactService.updateById(userContact);
        if (userFlag == false) {
            throw new UserException(EnumUserStatusCode.USER_CONTACT_UPDATE_FAIL_EXCEPTION);
        }
        return Response.ok(userContact.getId().toString());
    }

    @Override
    public Response<Boolean> deleteUserContactById(String id) {
        //1、获取对应数据
        UserContact userContact = userContactService.getById(id);
        if (userContact == null) {
            throw new UserException(EnumUserStatusCode.USER_CONTACT_IS_NOT_EXIST_EXCEPTION);
        }
        //1、删除用户联系人关系
        LambdaUpdateWrapper<UserContact> contactWrapper = Wrappers.lambdaUpdate(UserContact.class);
        contactWrapper.set(UserContact::getDeleted, true)
                .eq(UserContact::getId, id);
        return Response.ok(userContactService.update(contactWrapper));
    }
}
