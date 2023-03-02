package org.dows.user.biz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.Response;
import org.dows.user.api.api.UserAddressApi;
import org.dows.user.api.dto.UserAddressDTO;
import org.dows.user.api.vo.UserAddressVo;
import org.dows.user.api.vo.UserDwellingVo;
import org.dows.user.entity.UserAddress;
import org.dows.user.entity.UserDwelling;
import org.dows.user.enums.EnumUserStatusCode;
import org.dows.user.exception.UserException;
import org.dows.user.service.UserAddressService;
import org.dows.user.service.UserDwellingService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 * @date 2023/2/16 17:15
 */
@RequiredArgsConstructor
@Slf4j
@Service
@DS("uim")
public class UserAddressBiz implements UserAddressApi {

    private final UserAddressService userAddressService;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Response<String> insertUserAddress(UserAddressDTO userAddressDTO) {
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(userAddressDTO, userAddress);
        boolean userFlag = userAddressService.save(userAddress);
        if (userFlag == false) {
            throw new UserException(EnumUserStatusCode.USER_ADDRESS_CREATE_FAIL_EXCEPTION);
        }
        return Response.ok(userAddress.getId().toString());
    }

    @Override
    public Response<UserAddressVo> getUserAddressByUserId(String userId) {
        LambdaQueryWrapper<UserAddress> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserAddress::getUserId, userId);
        UserAddress userAddress = userAddressService.getOne(queryWrapper);
        //复制属性
        UserAddressVo vo = new UserAddressVo();
        if (userAddress != null) {
            BeanUtils.copyProperties(userAddress, vo);
            vo.setId(userAddress.getId().toString());
        }
        return Response.ok(vo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Response<String> updateUserAddressById(UserAddressDTO userAddressDTO) {
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(userAddressDTO, userAddress);
        userAddress.setId(Long.valueOf(userAddressDTO.getId()));
        boolean userFlag = userAddressService.updateById(userAddress);
        if (userFlag == false) {
            throw new UserException(EnumUserStatusCode.USER_ADDRESS_UPDATE_FAIL_EXCEPTION);
        }
        return Response.ok(userAddress.getId().toString());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Response<Boolean> deleteUserAddressById(String id) {
        boolean flag = false;
        //1、获取对应数据
        UserAddress userAddress = userAddressService.getById(id);
        if (userAddress != null) {
            //1、删除用户住址
            LambdaUpdateWrapper<UserAddress> addressWrapper = Wrappers.lambdaUpdate(UserAddress.class);
            addressWrapper.set(UserAddress::getDeleted, true)
                    .eq(UserAddress::getId, id);
            flag = userAddressService.update(addressWrapper);
        }
        return Response.ok(flag);
    }
}
