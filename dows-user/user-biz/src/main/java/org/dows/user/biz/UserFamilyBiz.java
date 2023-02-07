package org.dows.user.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.vo.AccountGroupVo;
import org.dows.account.vo.AccountOrgVo;
import org.dows.framework.api.Response;
import org.dows.user.api.api.UserFamilyApi;
import org.dows.user.api.api.UserInstanceApi;
import org.dows.user.api.dto.UserFamilyDTO;
import org.dows.user.api.vo.UserFamilyVo;
import org.dows.user.entity.UserFamily;
import org.dows.user.entity.UserInstance;
import org.dows.user.enums.EnumUserStatusCode;
import org.dows.user.exception.UserException;
import org.dows.user.service.UserFamilyService;
import org.dows.user.service.UserInstanceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author：jiangxia
 * @Date：2023/01/10 13:26
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class UserFamilyBiz implements UserFamilyApi {

    private final UserFamilyService userFamilyService;

    private final UserInstanceService userInstanceService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<UserFamilyVo> getGenealogyList(String userId) {
        //1、判断是否为户主
        LambdaQueryWrapper<UserFamily> queryWrapper = new LambdaQueryWrapper<>();
        UserFamily userFamily = userFamilyService.getOne(queryWrapper.eq(UserFamily::getUserId, userId)
                .eq(UserFamily::getDeleted, false));
        if (userFamily == null) {
            throw new UserException(EnumUserStatusCode.USER_IS_NOT_EXIST_EXCEPTION);
        }

        //2、如果不为户主，找寻对应家庭，进而找到家庭中的户主
        UserFamily familyHouseholder = new UserFamily();
        if (userFamily.getHouseholder() == false) {
            String familyId = userFamily.getFamilyId();
            LambdaQueryWrapper<UserFamily> familyWrapper = new LambdaQueryWrapper<>();
            familyHouseholder = userFamilyService.getOne(queryWrapper.eq(UserFamily::getFamilyId, familyId)
                    .eq(UserFamily::getHouseholder, true)
                    .eq(UserFamily::getDeleted, false));
        } else {
            familyHouseholder = userFamily;
        }
        UserFamilyDTO householderDTO = new UserFamilyDTO();
        BeanUtils.copyProperties(familyHouseholder, householderDTO);

        //3、获取所有家庭成员
        List<UserFamily> userFamilyList = userFamilyService.list();
        List<UserFamilyDTO> userFamilyDTOList = new ArrayList<>();
        userFamilyList.forEach(user -> {
            UserFamilyDTO dto = new UserFamilyDTO();
            BeanUtils.copyProperties(user, dto);
            userFamilyDTOList.add(dto);
        });

        //4、寻找上三代
        List<UserFamilyDTO> parentList = createParentList(householderDTO, userFamilyDTOList);
        householderDTO.setParent(parentList);

        //5、寻找下三代
        List<UserFamilyDTO> childList = createChildList(householderDTO, userFamilyDTOList);
        householderDTO.setParent(childList);

        //6、复制属性
        UserFamilyVo vo = new UserFamilyVo();
        BeanUtils.copyProperties(householderDTO, vo);
        return Response.ok(vo);
    }

    /**
     * 递归获取
     * 根据当前户主，获取上三代
     */
    private static List<UserFamilyDTO> createParentList(UserFamilyDTO familyHouseholder, List<UserFamilyDTO> list) {
/*        return list.stream().filter(model -> familyHouseholder.getParentId().equals(model.getUserId()))
                .peek(model -> model.setChildren(createParentList(model, list)))
                .collect(Collectors.toList());*/
        Integer num = 0;
        List<UserFamilyDTO> dtoList = list.stream().filter(model -> familyHouseholder.getParentId().equals(model.getUserId())).collect(Collectors.toList());
        if (dtoList.size() > 0) {
            if (num < 3) {
                //找到上一层对应关系
                dtoList.forEach(model -> {
                    model.setChildren(createParentList(model, list));
                });
                num++;
            }
        }
        return dtoList;
    }

    /**
     * 递归获取
     * 根据当前户主，获取下三代
     */
    private static List<UserFamilyDTO> createChildList(UserFamilyDTO familyHouseholder, List<UserFamilyDTO> list) {
        Integer num = 0;
        List<UserFamilyDTO> dtoList = list.stream().filter(model -> familyHouseholder.getUserId().equals(model.getParentId())).collect(Collectors.toList());
        if (dtoList.size() > 0) {
            if (num < 3) {
                //找到上一层对应关系
                dtoList.forEach(model -> {
                    model.setChildren(createParentList(model, list));
                });
                num++;
            }
        }
        return dtoList;
    }

    /**
     * 新增 用户-家庭
     *
     * @param userFamilyDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Long> insertUserFamily(UserFamilyDTO userFamilyDTO) {
        UserFamily model = new UserFamily();
        BeanUtils.copyProperties(userFamilyDTO, model);
        boolean flag = userFamilyService.save(model);
        if (flag == false) {
            throw new UserException(EnumUserStatusCode.USER_FAMILY_CREATE_FAIL_EXCEPTION);
        }
        return Response.ok(model.getId());
    }


    /**
     * 新增 用户-家庭
     *
     * @param userFamilyDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Long> updateUserFamilyById(UserFamilyDTO userFamilyDTO) {
        UserFamily userFamily = new UserFamily();
        BeanUtils.copyProperties(userFamilyDTO, userFamily);
        boolean userFlag = userFamilyService.updateById(userFamily);
        if (userFlag == false) {
            throw new UserException(EnumUserStatusCode.USER_FAMILY_UPDATE_FAIL_EXCEPTION);
        }
        return Response.ok(userFamily.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDeleteUserFamilys(List<String> ids) {
        ids.forEach(id -> {
            LambdaUpdateWrapper<UserFamily> familyWrapper = Wrappers.lambdaUpdate(UserFamily.class);
            familyWrapper.set(UserFamily::getDeleted, true)
                    .eq(UserFamily::getId, id);
            userFamilyService.update(familyWrapper);
        });
    }

    @Override
    public Response<IPage<UserFamilyVo>> getFamilyArchivesList(UserFamilyDTO userFamilyDTO) {
        //1、根据用户名、身份证号、手机号、居住地址获取对应的userId
        Set<String> userIds = new HashSet<>();
        if (StringUtils.isNotEmpty(userFamilyDTO.getNameNoPhoneAddress())) {
            List<UserInstance> userInstancesList = userInstanceService.lambdaQuery()
                    .select(UserInstance::getUserId)
                    .and(StringUtils.isNotEmpty(userFamilyDTO.getNameNoPhoneAddress()), t -> t.like(UserInstance::getName, userFamilyDTO.getNameNoPhoneAddress())
                            .or().like(UserInstance::getIdNo, userFamilyDTO.getNameNoPhoneAddress())
                            .or().like(UserInstance::getPhone, userFamilyDTO.getNameNoPhoneAddress()))
                    .or().like(UserInstance::getResidential, userFamilyDTO.getNameNoPhoneAddress())
                    .list();
            if (userInstancesList != null && userInstancesList.size() > 0) {
                userInstancesList.forEach(userInstance -> {
                    userIds.add(userInstance.getUserId());
                });
            }
            if (userIds.size() == 0) {
                userIds.add("fill");
            }
        }
        //2、根据
        //1、获取以户主为主体的家庭信息
        LambdaQueryWrapper<UserFamily> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(userFamilyDTO.getParentId()), UserFamily::getParentId, userFamilyDTO.getParentId())
                .eq(StringUtils.isNotEmpty(userFamilyDTO.getFamilyId()), UserFamily::getFamilyId, userFamilyDTO.getFamilyId())
                .eq(StringUtils.isNotEmpty(userFamilyDTO.getUserId()), UserFamily::getUserId, userFamilyDTO.getUserId())
                .in(userIds != null && userIds.size() > 0,UserFamily::getUserId,userIds)
                .eq(StringUtils.isNotEmpty(userFamilyDTO.getMemberId()), UserFamily::getMemberId, userFamilyDTO.getMemberId())
                .like(StringUtils.isNotEmpty(userFamilyDTO.getRelation()), UserFamily::getRelation, userFamilyDTO.getRelation())
                .eq(userFamilyDTO.getHouseholder() != null, UserFamily::getHouseholder, userFamilyDTO.getHouseholder())
                .eq(userFamilyDTO.getDt() != null, UserFamily::getDt, userFamilyDTO.getDt())
                .gt(userFamilyDTO.getStartTime() != null, UserFamily::getDt, userFamilyDTO.getStartTime())
                .lt(userFamilyDTO.getEndTime() != null, UserFamily::getDt, userFamilyDTO.getEndTime())
                .orderByDesc(UserFamily::getDt);
        Page<UserFamily> page = new Page<>(userFamilyDTO.getPageNo(), userFamilyDTO.getPageSize());
        IPage<UserFamily> familyPage = userFamilyService.page(page, queryWrapper);
        //2、获取该家庭的户主信息
        List<UserFamily> familyList = familyPage.getRecords();
        List<UserFamilyVo> voList = new ArrayList<>();
        IPage<UserFamilyVo> pageVo = new Page<>();
        if (familyList != null && familyList.size() > 0) {
            familyList.forEach(family -> {
/*                List<UserFamily> modelList = userFamilyService.lambdaQuery()
                        .eq(UserFamily::getFamilyId, family.getFamilyId())
                        .orderByDesc(UserFamily::getDt).list();
                //2.1、获取户主
                UserFamily model = new UserFamily();
                if(modelList != null && modelList.size() > 0){
                    model = modelList.stream().filter(item -> item.getHouseholder().equals("1")).findFirst().get();
                }*/
                //2.1、根据户主id获取户主姓名
                UserInstance userInstance = userInstanceService.getById(family.getUserId());
                //2.3、设置属性
                UserFamilyVo entity = new UserFamilyVo().builder().build()
                        .setId(family.getId())
                        .setHouseholderName(userInstance.getName())
                        .setIdNo(userInstance.getIdNo())
                        .setResidential(userInstance.getResidential())
                        .setCommunity(userInstance.getCommunity());
                voList.add(entity);
            });
        }
        pageVo.setRecords(voList);
        return Response.ok(pageVo);
    }
}
