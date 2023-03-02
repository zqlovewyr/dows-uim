package org.dows.user.biz;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.Response;
import org.dows.user.api.api.UserInstanceApi;
import org.dows.user.api.dto.UserInstanceDTO;
import org.dows.user.api.vo.UserInstanceVo;
import org.dows.user.constant.BaseConstant;
import org.dows.user.entity.UserInstance;
import org.dows.user.enums.EnumUserStatusCode;
import org.dows.user.exception.UserException;
import org.dows.user.service.UserInstanceService;
import org.dows.user.util.IDUtil;
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
public class UserInstanceBiz implements UserInstanceApi {
    private final UserInstanceService userInstanceService;

    /**
     * 自定义查询 组成员列表
     *
     * @param userInstanceDTO
     * @return Response<IPage < UserInstanceVo>>
     */
    @Override
    public Response<IPage<UserInstanceVo>> userInstanceUnionList(UserInstanceDTO userInstanceDTO) {
        LambdaQueryWrapper<UserInstance> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .in(userInstanceDTO.getIds() != null && userInstanceDTO.getIds().size() > 0, UserInstance::getId, userInstanceDTO.getIds())
                .in(userInstanceDTO.getUserIds() != null && userInstanceDTO.getUserIds().size() > 0, UserInstance::getUserId, userInstanceDTO.getUserIds())
                .and(StringUtils.isNotEmpty(userInstanceDTO.getNameNoPhone()), t -> t.like(UserInstance::getName, userInstanceDTO.getNameNoPhone()).or().like(UserInstance::getIdNo, userInstanceDTO.getNameNoPhone()).or().like(UserInstance::getPhone, userInstanceDTO.getNameNoPhone()))
                .like(StringUtils.isNotEmpty(userInstanceDTO.getName()), UserInstance::getName, userInstanceDTO.getName())
                .like(StringUtils.isNotEmpty(userInstanceDTO.getIdNo()), UserInstance::getIdNo, userInstanceDTO.getIdNo())
                .eq(StringUtils.isNotEmpty(userInstanceDTO.getAge()), UserInstance::getAge, userInstanceDTO.getAge())
                .like(StringUtils.isNotEmpty(userInstanceDTO.getNation()), UserInstance::getNation, userInstanceDTO.getNation())
                .like(StringUtils.isNotEmpty(userInstanceDTO.getSignOrg()), UserInstance::getSignOrg, userInstanceDTO.getSignOrg())
                .like(StringUtils.isNotEmpty(userInstanceDTO.getDomicile()), UserInstance::getDomicile, userInstanceDTO.getDomicile())
                .eq(userInstanceDTO.getBirthday() != null, UserInstance::getBirthday, userInstanceDTO.getBirthday())
                .gt(userInstanceDTO.getBirthdayStartTime() != null, UserInstance::getBirthday, userInstanceDTO.getBirthdayStartTime())
                .lt(userInstanceDTO.getBirthdayEndTime() != null, UserInstance::getBirthday, userInstanceDTO.getBirthdayEndTime())
                .gt(userInstanceDTO.getBirthdayStartTime() != null, UserInstance::getBirthday, userInstanceDTO.getBirthdayStartTime())
                .lt(userInstanceDTO.getBirthdayEndTime() != null, UserInstance::getBirthday, userInstanceDTO.getBirthdayEndTime())
                .gt(userInstanceDTO.getUserDate() != null, UserInstance::getDt, userInstanceDTO.getUserDate())
                .lt(userInstanceDTO.getUserDate() != null, UserInstance::getDt, userInstanceDTO.getUserDate())
                .eq(userInstanceDTO.getDt() != null, UserInstance::getDt, userInstanceDTO.getDt())
                .gt(userInstanceDTO.getStartTime() != null, UserInstance::getDt, userInstanceDTO.getStartTime())
                .lt(userInstanceDTO.getEndTime() != null, UserInstance::getDt, userInstanceDTO.getEndTime())
                .eq(UserInstance::getDeleted, false);
        //判断按什么排序,如果不按名称排序，则按默认的创建时间排序
        if (StringUtils.isNotEmpty(userInstanceDTO.getUserNameType())) {
            if (userInstanceDTO.getUserNameType().equals("down")) {
                queryWrapper.orderByDesc(UserInstance::getName);
            }
            if (userInstanceDTO.getUserNameType().equals("up")) {
                queryWrapper.orderByAsc(UserInstance::getName);
            }
        }
        if (StringUtils.isNotEmpty(userInstanceDTO.getDtType())) {
            if (userInstanceDTO.getDtType().equals("down")) {
                queryWrapper.orderByDesc(UserInstance::getDt);
            }
            if (userInstanceDTO.getDtType().equals("up")) {
                queryWrapper.orderByAsc(UserInstance::getDt);
            }
        } else {
            queryWrapper.orderByDesc(UserInstance::getDt);
        }
        Page<UserInstance> page = new Page<>(userInstanceDTO.getPageNo(), userInstanceDTO.getPageSize());
        IPage<UserInstance> userPage = userInstanceService.page(page, queryWrapper);
        List<UserInstance> voList = userPage.getRecords();
        //复制属性
        IPage<UserInstanceVo> pageVo = new Page<>();
        List<UserInstanceVo> resultList = new ArrayList<>();
        BeanUtils.copyProperties(userPage, pageVo, new String[]{"records"});
        if (voList != null && voList.size() > 0) {
            voList.forEach(instance -> {
                UserInstanceVo vo = new UserInstanceVo();
                BeanUtils.copyProperties(instance, vo);
                vo.setId(instance.getId().toString());
                resultList.add(vo);
            });
        }
        pageVo.setRecords(resultList);
        return Response.ok(pageVo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Response<String> insertUserInstance(UserInstanceDTO userInstanceDTO) {
        UserInstance userInstance = new UserInstance();
        BeanUtils.copyProperties(userInstanceDTO, userInstance);
        userInstance.setUserId(String.valueOf(IDUtil.getId(BaseConstant.WORKER_ID)));
        boolean userFlag = userInstanceService.save(userInstance);
        if (userFlag == false) {
            throw new UserException(EnumUserStatusCode.USER_CREATE_FAIL_EXCEPTION);
        }
        return Response.ok(userInstance.getId().toString());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Response<String> updateUserInstance(UserInstanceDTO userInstanceDTO) {
        UserInstance userInstance = new UserInstance();
        BeanUtils.copyProperties(userInstanceDTO, userInstance);
        userInstance.setId(Long.valueOf(userInstanceDTO.getId()));
        boolean userFlag = userInstanceService.updateById(userInstance);
        if (userFlag == false) {
            throw new UserException(EnumUserStatusCode.USER_CREATE_FAIL_EXCEPTION);
        }
        return Response.ok(userInstance.getId().toString());
    }

    @Override
    public Response<UserInstanceVo> getUserInstanceById(String id) {
        UserInstance userInstance = userInstanceService.getById(Long.valueOf(id));
        //复制属性
        UserInstanceVo vo = new UserInstanceVo();
        if (userInstance != null) {
            BeanUtils.copyProperties(userInstance, vo);
            vo.setId(userInstance.getId().toString());
        }
        return Response.ok(vo);
    }

    @Override
    public Response<List<UserInstanceVo>> getUserInstanceFilterList(UserInstanceDTO userInstanceDTO) {
        List<UserInstance> userInstanceList = userInstanceService.lambdaQuery()
                .like(StringUtils.isNotEmpty(userInstanceDTO.getName()), UserInstance::getName, userInstanceDTO.getName())
                .eq(StringUtils.isNotEmpty(userInstanceDTO.getGender()), UserInstance::getGender, userInstanceDTO.getGender())
                .eq(StringUtils.isNotEmpty(userInstanceDTO.getPhone()), UserInstance::getPhone, userInstanceDTO.getPhone())
                .eq(UserInstance::getDeleted, false)
                .list();
        //复制属性
        List<UserInstanceVo> voList = new ArrayList<>();
        userInstanceList.forEach(user -> {
            UserInstanceVo vo = new UserInstanceVo();
            BeanUtils.copyProperties(user, vo);
            vo.setId(user.getId().toString());
            voList.add(vo);
        });
        return Response.ok(voList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Response<Boolean> deleteUserInstanceById(String id) {
        //1、获取对应数据
        UserInstance userInstance = userInstanceService.getById(id);
        if (userInstance == null) {
            throw new UserException(EnumUserStatusCode.USER_IS_NOT_EXIST_EXCEPTION);
        }
        //1、删除组织架构
        LambdaUpdateWrapper<UserInstance> instanceWrapper = Wrappers.lambdaUpdate(UserInstance.class);
        instanceWrapper.set(UserInstance::getDeleted, true)
                .eq(UserInstance::getId, id);
        return Response.ok(userInstanceService.update(instanceWrapper));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public Response deleteUserInstances(List<String> ids) {
        Integer count = 0;
        for (String id : ids) {
            //1、获取对应数据
            UserInstance userInstance = userInstanceService.getById(id);
            if (userInstance == null) {
                return Response.fail(EnumUserStatusCode.USER_IS_NOT_EXIST_EXCEPTION);
            }
            //1、删除组织架构
            LambdaUpdateWrapper<UserInstance> instanceWrapper = Wrappers.lambdaUpdate(UserInstance.class);
            instanceWrapper.set(UserInstance::getDeleted, true)
                    .eq(UserInstance::getId, id);
            boolean flag = userInstanceService.update(instanceWrapper);
            if (flag) {
                count++;
            }
        }
        return Response.ok(count);
    }

    @Override
    public Response<List<UserInstanceVo>> getUserInstanceListNoPage(UserInstanceDTO userInstanceDTO) {
        List<UserInstance> instanceList = userInstanceService.lambdaQuery().in(userInstanceDTO.getUserIds() != null && userInstanceDTO.getUserIds().size() > 0, UserInstance::getUserId, userInstanceDTO.getUserIds())
                .and(StringUtils.isNotEmpty(userInstanceDTO.getNameNoPhone()), t -> t.like(UserInstance::getName, userInstanceDTO.getNameNoPhone()).or().like(UserInstance::getIdNo, userInstanceDTO.getNameNoPhone()).or().like(UserInstance::getPhone, userInstanceDTO.getNameNoPhone()))
                .like(StringUtils.isNotEmpty(userInstanceDTO.getName()), UserInstance::getName, userInstanceDTO.getName())
                .like(StringUtils.isNotEmpty(userInstanceDTO.getIdNo()), UserInstance::getIdNo, userInstanceDTO.getIdNo())
                .eq(StringUtils.isNotEmpty(userInstanceDTO.getAge()), UserInstance::getAge, userInstanceDTO.getAge())
                .like(StringUtils.isNotEmpty(userInstanceDTO.getNation()), UserInstance::getNation, userInstanceDTO.getNation())
                .like(StringUtils.isNotEmpty(userInstanceDTO.getSignOrg()), UserInstance::getSignOrg, userInstanceDTO.getSignOrg())
                .like(StringUtils.isNotEmpty(userInstanceDTO.getDomicile()), UserInstance::getDomicile, userInstanceDTO.getDomicile())
                .eq(userInstanceDTO.getBirthday() != null, UserInstance::getBirthday, userInstanceDTO.getBirthday())
                .gt(userInstanceDTO.getBirthdayStartTime() != null, UserInstance::getBirthday, userInstanceDTO.getBirthdayStartTime())
                .lt(userInstanceDTO.getBirthdayEndTime() != null, UserInstance::getBirthday, userInstanceDTO.getBirthdayEndTime())
                .gt(userInstanceDTO.getBirthdayStartTime() != null, UserInstance::getBirthday, userInstanceDTO.getBirthdayStartTime())
                .lt(userInstanceDTO.getBirthdayEndTime() != null, UserInstance::getBirthday, userInstanceDTO.getBirthdayEndTime())
                .gt(userInstanceDTO.getUserDate() != null, UserInstance::getDt, userInstanceDTO.getUserDate())
                .lt(userInstanceDTO.getUserDate() != null, UserInstance::getDt, userInstanceDTO.getUserDate())
                .eq(userInstanceDTO.getDt() != null, UserInstance::getDt, userInstanceDTO.getDt())
                .gt(userInstanceDTO.getStartTime() != null, UserInstance::getDt, userInstanceDTO.getStartTime())
                .lt(userInstanceDTO.getEndTime() != null, UserInstance::getDt, userInstanceDTO.getEndTime())
                .eq(UserInstance::getDeleted, false)
                .orderByDesc(UserInstance::getDt).list();
        //复制属性
        List<UserInstanceVo> voList = new ArrayList<>();
        if (instanceList != null && instanceList.size() > 0) {
            instanceList.forEach(instance -> {
                UserInstanceVo vo = new UserInstanceVo();
                BeanUtils.copyProperties(instance, vo);
                vo.setId(instance.getId().toString());
                voList.add(vo);
            });
        }
        return Response.ok(voList);
    }
}
