package org.dows.user.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
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
import org.dows.user.api.vo.UserInstanceVo;
import org.dows.user.entity.UserAddress;
import org.dows.user.entity.UserDwelling;
import org.dows.user.entity.UserFamily;
import org.dows.user.entity.UserInstance;
import org.dows.user.enums.EnumUserStatusCode;
import org.dows.user.exception.UserException;
import org.dows.user.service.UserAddressService;
import org.dows.user.service.UserDwellingService;
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

    private final UserDwellingService userDwellingService;

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

    @Override
    public Response<UserFamilyVo> getUserFamilyByUserId(String userId) {
        LambdaQueryWrapper<UserFamily> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserFamily::getUserId, userId);
        UserFamily userFamily = userFamilyService.getOne(queryWrapper);
        //复制属性
        UserFamilyVo vo = new UserFamilyVo();
        if (userFamily != null) {
            BeanUtils.copyProperties(userFamily, vo);
            vo.setId(userFamily.getId().toString());
        }
        return Response.ok(vo);
    }

    @Override
    public Response<UserFamilyVo> getUserFamilyById(String id) {
        LambdaQueryWrapper<UserFamily> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserFamily::getId, Long.valueOf(id));
        UserFamily userFamily = userFamilyService.getOne(queryWrapper);
        //复制属性
        UserFamilyVo vo = new UserFamilyVo();
        if (userFamily != null) {
            BeanUtils.copyProperties(userFamily, vo);
            vo.setId(userFamily.getId().toString());
        }
        return Response.ok(vo);
    }

    /**
     * 递归获取
     * 根据当前户主，获取上三代
     */
    private static List<UserFamilyDTO> createParentList(UserFamilyDTO familyHouseholder, List<UserFamilyDTO> list) {
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
    public Response<String> insertUserFamily(UserFamilyDTO userFamilyDTO) {
        UserFamily model = new UserFamily();
        BeanUtils.copyProperties(userFamilyDTO, model);
        model.setFamilyId(IdWorker.getIdStr());
        boolean flag = userFamilyService.save(model);
        if (flag == false) {
            throw new UserException(EnumUserStatusCode.USER_FAMILY_CREATE_FAIL_EXCEPTION);
        }
        return Response.ok(model.getId().toString());
    }


    /**
     * 新增 用户-家庭
     *
     * @param userFamilyDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<String> updateUserFamilyById(UserFamilyDTO userFamilyDTO) {
        UserFamily userFamily = new UserFamily();
        BeanUtils.copyProperties(userFamilyDTO, userFamily);
        userFamily.setId(Long.valueOf(userFamilyDTO.getId()));
        boolean userFlag = userFamilyService.updateById(userFamily);
        if (userFlag == false) {
            throw new UserException(EnumUserStatusCode.USER_FAMILY_UPDATE_FAIL_EXCEPTION);
        }
        return Response.ok(userFamily.getId().toString());
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
        //1、根据用户名、身份证号、手机号获取对应的userId
        Set<String> userIds = new HashSet<>();
        Set<String> familyIds = new HashSet<>();
        if (StringUtils.isNotEmpty(userFamilyDTO.getNameNoPhoneAddress())) {
            List<UserInstance> userInstancesList = userInstanceService.lambdaQuery()
                    .select(UserInstance::getId)
                    .and(StringUtils.isNotEmpty(userFamilyDTO.getNameNoPhoneAddress()), t -> t.like(UserInstance::getName, userFamilyDTO.getNameNoPhoneAddress())
                            .or().like(UserInstance::getIdNo, userFamilyDTO.getNameNoPhoneAddress())
                            .or().like(UserInstance::getPhone, userFamilyDTO.getNameNoPhoneAddress()))
                    .list();
            if (userInstancesList != null && userInstancesList.size() > 0) {
                userInstancesList.forEach(userInstance -> {
                    userIds.add(userInstance.getId().toString());
                });
            }
            if (userIds.size() == 0) {
                userIds.add("fill");
            }
        }
        //2、根据居住地址获取对应的userId
        if (StringUtils.isNotEmpty(userFamilyDTO.getNameNoPhoneAddress())) {
            List<UserDwelling> userDwellingList = userDwellingService.lambdaQuery()
                    .select(UserDwelling::getFamilyId)
                    .like(StringUtils.isNotEmpty(userFamilyDTO.getNameNoPhoneAddress()), UserDwelling::getAddress, userFamilyDTO.getNameNoPhoneAddress())
                    .list();
            if (userDwellingList != null && userDwellingList.size() > 0) {
                userDwellingList.forEach(userAddress -> {
                    familyIds.add(userAddress.getFamilyId());
                });
            }
            if (familyIds.size() == 0) {
                familyIds.add("fill");
            }
        }
        //3、获取以户主为主体的家庭信息
        LambdaQueryWrapper<UserFamily> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(userFamilyDTO.getParentId()), UserFamily::getParentId, userFamilyDTO.getParentId())
                .eq(StringUtils.isNotEmpty(userFamilyDTO.getFamilyId()), UserFamily::getFamilyId, userFamilyDTO.getFamilyId())
                .in(familyIds != null && familyIds.size() > 0, UserFamily::getId, familyIds)
                .eq(StringUtils.isNotEmpty(userFamilyDTO.getUserId()), UserFamily::getUserId, userFamilyDTO.getUserId())
                .in(userIds != null && userIds.size() > 0, UserFamily::getUserId, userIds)
                .eq(StringUtils.isNotEmpty(userFamilyDTO.getMemberId()), UserFamily::getMemberId, userFamilyDTO.getMemberId())
                .like(StringUtils.isNotEmpty(userFamilyDTO.getRelation()), UserFamily::getRelation, userFamilyDTO.getRelation())
                .eq(userFamilyDTO.getHouseholder() != null, UserFamily::getHouseholder, userFamilyDTO.getHouseholder())
                .eq(userFamilyDTO.getDt() != null, UserFamily::getDt, userFamilyDTO.getDt())
                .gt(userFamilyDTO.getStartTime() != null, UserFamily::getDt, userFamilyDTO.getStartTime())
                .lt(userFamilyDTO.getEndTime() != null, UserFamily::getDt, userFamilyDTO.getEndTime())
                .orderByDesc(UserFamily::getDt);
        Page<UserFamily> page = new Page<>(userFamilyDTO.getPageNo(), userFamilyDTO.getPageSize());
        IPage<UserFamily> familyPage = userFamilyService.page(page, queryWrapper);
        //4、获取该家庭的户主信息
        List<UserFamily> familyList = familyPage.getRecords();
        List<UserFamilyVo> voList = new ArrayList<>();
        IPage<UserFamilyVo> pageVo = new Page<>();
        if (familyList != null && familyList.size() > 0) {
            familyList.forEach(family -> {
                //4.1、根据户主id获取户主姓名
                UserInstance userInstance = userInstanceService.getById(family.getUserId());
                //4.2、获取户主地址和社区
                UserDwelling userDwelling = userDwellingService.lambdaQuery()
                        .eq(UserDwelling::getFamilyId, family.getId())
                        .one();
                //4.3、设置属性
                UserFamilyVo entity = new UserFamilyVo().builder().build()
                        .setId(family.getId().toString())
                        .setHouseholderName(userInstance.getName())
                        .setIdNo(userInstance.getIdNo())
                        .setResidential(userDwelling.getAddress())
                        .setCommunity(userDwelling.getCommunity());
                voList.add(entity);
                // TODO 判断是否存在个人档案或者客户管理相关信息，如果有，则获取所属健管师

            });
        }
        pageVo.setRecords(voList);
        return Response.ok(pageVo);
    }

    @Override
    public Response<IPage<UserFamilyVo>> getFamilyMemberList(UserFamilyDTO userFamilyDTO) {
        //1、根据用户名、身份证号获取对应的userId
        Set<String> userIds = new HashSet<>();
        if (StringUtils.isNotEmpty(userFamilyDTO.getNameNoPhoneAddress())) {
            List<UserInstance> userInstancesList = userInstanceService.lambdaQuery()
                    .select(UserInstance::getId)
                    .and(StringUtils.isNotEmpty(userFamilyDTO.getNameNoPhoneAddress()), t -> t.like(UserInstance::getName, userFamilyDTO.getNameNoPhoneAddress())
                            .or().like(UserInstance::getIdNo, userFamilyDTO.getNameNoPhoneAddress()))
                    .list();
            if (userInstancesList != null && userInstancesList.size() > 0) {
                userInstancesList.forEach(userInstance -> {
                    userIds.add(userInstance.getId().toString());
                });
            }
            if (userIds.size() == 0) {
                userIds.add("fill");
            }
        }
        //2、获取家庭成员不包含户主
        LambdaQueryWrapper<UserFamily> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(userFamilyDTO.getId() != null, UserFamily::getId, userFamilyDTO.getId())
                .eq(StringUtils.isNotEmpty(userFamilyDTO.getParentId()), UserFamily::getParentId, userFamilyDTO.getParentId())
                .eq(StringUtils.isNotEmpty(userFamilyDTO.getFamilyId()), UserFamily::getFamilyId, userFamilyDTO.getFamilyId())
                .eq(StringUtils.isNotEmpty(userFamilyDTO.getUserId()), UserFamily::getUserId, userFamilyDTO.getUserId())
                .in(userIds != null && userIds.size() > 0, UserFamily::getUserId, userIds)
                .eq(StringUtils.isNotEmpty(userFamilyDTO.getMemberId()), UserFamily::getMemberId, userFamilyDTO.getMemberId())
                .like(StringUtils.isNotEmpty(userFamilyDTO.getRelation()), UserFamily::getRelation, userFamilyDTO.getRelation())
                .eq(userFamilyDTO.getHouseholder() != null, UserFamily::getHouseholder, 0)
                .eq(userFamilyDTO.getDt() != null, UserFamily::getDt, userFamilyDTO.getDt())
                .gt(userFamilyDTO.getStartTime() != null, UserFamily::getDt, userFamilyDTO.getStartTime())
                .lt(userFamilyDTO.getEndTime() != null, UserFamily::getDt, userFamilyDTO.getEndTime())
                .orderByDesc(UserFamily::getDt);
        Page<UserFamily> page = new Page<>(userFamilyDTO.getPageNo(), userFamilyDTO.getPageSize());
        IPage<UserFamily> familyPage = userFamilyService.page(page, queryWrapper);
        //4、获取该家庭的户主信息
        List<UserFamily> familyList = familyPage.getRecords();
        List<UserFamilyVo> voList = new ArrayList<>();
        IPage<UserFamilyVo> pageVo = new Page<>();
        if (familyList != null && familyList.size() > 0) {
            familyList.forEach(family -> {
                //4.1、根据户主id获取户主姓名
                UserInstance userInstance = userInstanceService.getById(family.getUserId());
                //4.2、设置属性
                UserFamilyVo entity = UserFamilyVo.builder()
                        .memberName(userInstance.getName())
                        .id(userInstance.getId().toString())
                        .idNo(userInstance.getIdNo())
                        .gender(userInstance.getGender())
                        .relation(family.getRelation()).build();
                voList.add(entity);
            });
        }
        pageVo.setRecords(voList);
        return Response.ok(pageVo);
    }

    @Override
    public Response<Boolean> deleteUserFamilyById(String id) {
        //1、获取对应数据
        UserFamily userFamily = userFamilyService.getById(id);
        if (userFamily == null) {
            throw new UserException(EnumUserStatusCode.USER_FAMILY_IS_NOT_EXIST_EXCEPTION);
        }
        //1、删除用户家庭关系
        LambdaUpdateWrapper<UserFamily> familyWrapper = Wrappers.lambdaUpdate(UserFamily.class);
        familyWrapper.set(UserFamily::getDeleted, true)
                .eq(UserFamily::getId, id);
        return Response.ok(userFamilyService.update(familyWrapper));
    }
}
