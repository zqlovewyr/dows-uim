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
import org.dows.framework.api.Response;
import org.dows.user.api.api.UserFamilyApi;
import org.dows.user.api.dto.GenealogyDTO;
import org.dows.user.api.dto.UserFamilyDTO;
import org.dows.user.api.vo.UserFamilyVo;
import org.dows.user.constant.BaseConstant;
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
import org.dows.user.util.ReflectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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

    private final UserAddressService userAddressService;

    @Override
    public Response<GenealogyDTO> getGenealogyList(String id) {
        //父母兄弟姐妹
        List<UserFamilyVo> parentSiblingList = new ArrayList<>();
        //我们的表姐妹
        List<UserFamilyVo> ownerSiblingList = new ArrayList<>();
        //子女的表姐妹
        List<UserFamilyVo> childrenSiblingList = new ArrayList<>();
        //子女
        List<UserFamilyVo> childrenList = new ArrayList<>();
        //子女的子女
        List<UserFamilyVo> childrenOfChildList = new ArrayList<>();
        //子女表姐妹的子女
        List<UserFamilyVo> childrenOfChildSiblingList = new ArrayList<>();
        //第一代实体类
        GenealogyDTO firstDTO = new GenealogyDTO();
        List<GenealogyDTO> firstList = new ArrayList<>();
        //第二代实体类
        GenealogyDTO secondDTO = new GenealogyDTO();
        List<GenealogyDTO> secondList = new ArrayList<>();
        //第三代实体类
        GenealogyDTO thirdDTO = new GenealogyDTO();
        List<GenealogyDTO> thirdList = new ArrayList<>();
        //第四代实体类
        GenealogyDTO forthDTO = new GenealogyDTO();
        List<GenealogyDTO> forthList = new ArrayList<>();
        GenealogyDTO genealogyDTO = new GenealogyDTO();
        //1、判断是否为户主
        LambdaQueryWrapper<UserFamily> queryWrapper = new LambdaQueryWrapper<>();
        UserFamily userFamily = userFamilyService.getOne(queryWrapper.eq(UserFamily::getId, id).eq(UserFamily::getHouseholder, 1));
        if (userFamily == null) {
            throw new UserException(EnumUserStatusCode.USER_IS_NOT_EXIST_EXCEPTION);
        }
        //2、以该用户的家庭的爷爷辈,设置根节点
        List<UserFamilyVo> familyList = this.getUserFamilyListByFamilyId(userFamily.getFamilyId()).getData().stream().filter(user -> StringUtils.isNotEmpty(user.getRelation())).collect(Collectors.toList());
        if (familyList != null && familyList.size() > 0) {
            for (UserFamilyVo family : familyList) {
                if (family.getRelation().equals(BaseConstant.PARENT)) {
                    //3.1、判断以爸爸或者妈妈户主的家庭
                    LambdaQueryWrapper<UserFamily> parentWrapper = new LambdaQueryWrapper<>();
                    UserFamily householder = userFamilyService.getOne(parentWrapper.eq(UserFamily::getUserId, family.getUserId()).eq(UserFamily::getHouseholder, 1));
                    //3.2 获取以爸爸妈妈为户主的家庭中的父母
                    if (householder != null) {
                        //寻找父母的上一辈
                        LambdaQueryWrapper<UserFamily> grandWrapper = new LambdaQueryWrapper<>();
                        List<UserFamily> grandList = userFamilyService.list(grandWrapper.eq(UserFamily::getFamilyId, householder.getFamilyId()).eq(UserFamily::getRelation, BaseConstant.PARENT));
                        if (grandList != null && grandList.size() > 0) {
                            for (UserFamily grand : grandList) {
                                if (grand.getHouseholder()) {
                                    //设置属性
                                    UserInstance model = userInstanceService.getById(grand.getUserId());
                                    if (model != null) {
                                        genealogyDTO = GenealogyDTO.builder().id(model.getId().toString()).name(model.getName()).imageUrl(model.getAvatar()).classType("rootNode").gender(model.getGender()).build();
                                        break;
                                    }
                                }
                            }
                        } else {
                            //寻找父母兄弟姐妹的上一辈
                            LambdaQueryWrapper<UserFamily> siblingWrapper = new LambdaQueryWrapper<>();
                            List<UserFamily> siblingList = userFamilyService.list(siblingWrapper.eq(UserFamily::getFamilyId, householder.getFamilyId()).eq(UserFamily::getRelation, BaseConstant.SIBLING));
                            siblingList = siblingList.stream().filter(v -> v.getHouseholder()).collect(Collectors.toList());
                            if (siblingList != null && siblingList.size() > 0) {
                                UserFamily sibling = siblingList.get(0);
                                //获取父母兄弟姐妹的上一辈
                                parentSiblingList = this.getUserFamilyListByFamilyId(sibling.getFamilyId()).getData().stream().filter(family1 -> family1.getRelation().equals(BaseConstant.PARENT)).collect(Collectors.toList());
                                List<UserFamilyVo> resultFamilyList = parentSiblingList.stream().filter(result->result.getHouseholder()).collect(Collectors.toList());
                                if (resultFamilyList != null && resultFamilyList.size() > 0) {
                                    for (UserFamilyVo siblingGrand : resultFamilyList) {
                                        if (siblingGrand.getHouseholder()) {
                                            //设置属性
                                            UserInstance model = userInstanceService.getById(siblingGrand.getUserId());
                                            if (model != null) {
                                                genealogyDTO = GenealogyDTO.builder().id(model.getId().toString()).name(model.getName()).imageUrl(model.getAvatar()).classType("rootNode").gender(model.getGender()).build();
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
        //如果给根节点赋值成功，则设置虚拟值
        if (ReflectUtil.isObjectNull(genealogyDTO)) {
            genealogyDTO = GenealogyDTO.builder().name("root").classType("rootNode").gender("1").build();
        }
        //3、获取户主的父节点，包括父母和父母的兄弟姐妹作为爷爷节点的子节点
        for (UserFamilyVo family : familyList) {
            if (family.getRelation().equals(BaseConstant.PARENT)) {
                //4.1、设置父母属性
                UserInstance model = userInstanceService.getById(family.getUserId());
                firstDTO = GenealogyDTO.builder().id(model.getId().toString()).name(model.getName()).imageUrl(model.getAvatar()).gender(model.getGender()).build();
                firstList.add(firstDTO);
                //4.2、判断以爸爸或者妈妈户主的家庭
                LambdaQueryWrapper<UserFamily> parentWrapper = new LambdaQueryWrapper<>();
                UserFamily parent = userFamilyService.getOne(parentWrapper.eq(UserFamily::getUserId, family.getUserId()).eq(UserFamily::getHouseholder, 1));
                //说明是户主
                if (parent != null) {
                    //4.3、获取该户主爸爸妈妈的兄弟姐妹
                    if (parentSiblingList != null && parentSiblingList.size() > 0) {
                        for (UserFamilyVo vo : parentSiblingList) {
                            UserInstance firstModel = userInstanceService.getById(vo.getUserId());
                            firstList.add(GenealogyDTO.builder().id(firstModel.getId().toString()).name(firstModel.getName()).imageUrl(firstModel.getAvatar()).gender(firstModel.getGender()).build());
                        }
                    }
                }
            }
        }

        //4、获取自己这一代的兄弟姐妹，作为第二代
        //设置自己
        UserInstance owner = userInstanceService.getById(userFamily.getUserId());
        secondDTO = GenealogyDTO.builder().id(owner.getId().toString()).name(owner.getName()).imageUrl(owner.getAvatar()).gender(owner.getGender()).build();
        secondList.add(secondDTO);
        for (UserFamilyVo family : familyList) {
            if (family.getRelation().equals(BaseConstant.SIBLING)) {
                //5.1、设置兄弟姐妹属性
                UserInstance secondModel = userInstanceService.getById(family.getUserId());
                secondList.add(GenealogyDTO.builder().id(secondModel.getId().toString()).name(secondModel.getName()).imageUrl(secondModel.getAvatar()).gender(secondModel.getGender()).build());
            }
            //5.2、获取父母兄弟姐妹的子女
            if (family.getRelation().equals(BaseConstant.PARENT)) {
                //4.3、获取以该户主的兄弟姐妹，也就是爸爸的兄弟姐妹为户主的家庭的子女
                for (UserFamilyVo sibling : parentSiblingList) {
                    LambdaQueryWrapper<UserFamily> siblingWrapper = new LambdaQueryWrapper<>();
                    UserFamily sibling1 = userFamilyService.getOne(siblingWrapper.eq(UserFamily::getUserId, sibling.getUserId()).eq(UserFamily::getHouseholder, 1));
                    if (sibling1 != null) {
                        ownerSiblingList = this.getUserFamilyListByFamilyId(sibling1.getFamilyId()).getData().stream().filter(family1 -> family1.getRelation().equals(BaseConstant.CHILDREN)).collect(Collectors.toList());
                        for (UserFamilyVo child : ownerSiblingList) {
                            UserInstance secondModel = userInstanceService.getById(child.getUserId());
                            secondList.add(GenealogyDTO.builder().id(secondModel.getId().toString()).name(secondModel.getName()).imageUrl(secondModel.getAvatar()).gender(secondModel.getGender()).build());
                        }
                    }
                }
            }
        }
        firstDTO.setChildren(secondList);

        //5、获取自己的下一代及兄弟姐妹的下一代
        for (UserFamilyVo family : familyList) {
            //设置自己子女
            if (family.getRelation().equals(BaseConstant.CHILDREN)) {
                UserInstance model = userInstanceService.getById(family.getUserId());
                childrenList.add(family);
                thirdDTO = GenealogyDTO.builder().id(model.getId().toString()).name(model.getName()).imageUrl(model.getAvatar()).gender(model.getGender()).build();
                thirdList.add(thirdDTO);
            }
            //获取表姐妹的子女
            for (UserFamilyVo sibling : ownerSiblingList) {
                LambdaQueryWrapper<UserFamily> siblingWrapper = new LambdaQueryWrapper<>();
                UserFamily sibling1 = userFamilyService.getOne(siblingWrapper.eq(UserFamily::getUserId, sibling.getUserId()).eq(UserFamily::getHouseholder, 1));
                if (sibling1 != null) {
                    childrenSiblingList = this.getUserFamilyListByFamilyId(sibling1.getFamilyId()).getData().stream().filter(family1 -> family1.getRelation().equals(BaseConstant.CHILDREN)).collect(Collectors.toList());
                    for (UserFamilyVo vo : childrenSiblingList) {
                        UserInstance thirdModel = userInstanceService.getById(vo.getUserId());
                        childrenOfChildSiblingList.add(vo);
                        thirdDTO = GenealogyDTO.builder().id(thirdModel.getId().toString()).name(thirdModel.getName()).imageUrl(thirdModel.getAvatar()).gender(thirdModel.getGender()).build();
                        thirdList.add(thirdDTO);
                    }
                }
            }
        }
        secondDTO.setChildren(thirdList);

        //6、获取自己下一代的下一代及其兄弟姐妹
        for(UserFamilyVo vo : childrenList){
            LambdaQueryWrapper<UserFamily> siblingWrapper = new LambdaQueryWrapper<>();
            UserFamily sibling = userFamilyService.getOne(siblingWrapper.eq(UserFamily::getUserId, vo.getUserId()).eq(UserFamily::getHouseholder, 1));
            if(sibling != null){
                childrenOfChildList = this.getUserFamilyListByFamilyId(sibling.getFamilyId()).getData().stream().filter(family1 -> StringUtils.isNotEmpty(family1.getRelation()) && family1.getRelation().equals(BaseConstant.CHILDREN)).collect(Collectors.toList());
                for (UserFamilyVo model : childrenOfChildList) {
                    UserInstance forthModel = userInstanceService.getById(model.getUserId());
                    forthDTO = GenealogyDTO.builder().id(forthModel.getId().toString()).name(forthModel.getName()).imageUrl(forthModel.getAvatar()).gender(forthModel.getGender()).build();
                    forthList.add(forthDTO);
                }
            }
        }

        for(UserFamilyVo vo : childrenOfChildSiblingList){
            LambdaQueryWrapper<UserFamily> siblingWrapper = new LambdaQueryWrapper<>();
            UserFamily sibling = userFamilyService.getOne(siblingWrapper.eq(UserFamily::getUserId, vo.getUserId()).eq(UserFamily::getHouseholder, 1));
            if(sibling != null){
                childrenOfChildList = this.getUserFamilyListByFamilyId(sibling.getFamilyId()).getData().stream().filter(family1 -> StringUtils.isNotEmpty(family1.getRelation()) && family1.getRelation().equals(BaseConstant.CHILDREN)).collect(Collectors.toList());
                for (UserFamilyVo model : childrenOfChildList) {
                    UserInstance forthModel = userInstanceService.getById(model.getUserId());
                    forthDTO = GenealogyDTO.builder().id(forthModel.getId().toString()).name(forthModel.getName()).imageUrl(forthModel.getAvatar()).gender(forthModel.getGender()).build();
                    forthList.add(forthDTO);
                }
            }
        }
        thirdDTO.setChildren(forthList);
        genealogyDTO.setChildren(firstList);
        return Response.ok(genealogyDTO);

    }

    @Override
    public Response<List<UserFamilyVo>> getUserFamilyByUserId(String userId) {
        LambdaQueryWrapper<UserFamily> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserFamily::getUserId, userId);
        List<UserFamily> userFamilyList = userFamilyService.list(queryWrapper);
        //复制属性
        List<UserFamilyVo> voList = new ArrayList<>();
        if (userFamilyList != null && userFamilyList.size() > 0) {
            userFamilyList.forEach(userFamily -> {
                UserFamilyVo vo = new UserFamilyVo();
                BeanUtils.copyProperties(userFamily, vo);
                vo.setId(userFamily.getId().toString());
                voList.add(vo);
            });
        }
        return Response.ok(voList);
    }

    @Override
    public Response<UserFamilyVo> getUserFamilyByUserIdAndFamilyId(String userId, String familyId) {
        LambdaQueryWrapper<UserFamily> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserFamily::getUserId, userId);
        queryWrapper.eq(UserFamily::getFamilyId, familyId);
        UserFamily userFamily = userFamilyService.getOne(queryWrapper);
        //复制属性
        UserFamilyVo vo = new UserFamilyVo();
        if(userFamily != null){
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

    @Override
    public Response<List<UserFamilyVo>> getUserFamilyListByFamilyId(String familyId) {
        LambdaQueryWrapper<UserFamily> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserFamily::getFamilyId, familyId);
        List<UserFamily> userFamilyList = userFamilyService.list(queryWrapper);
        //复制属性
        List<UserFamilyVo> voList = new ArrayList<>();
        if (userFamilyList != null && userFamilyList.size() > 0) {
            userFamilyList.forEach(family -> {
                UserFamilyVo vo = new UserFamilyVo();
                BeanUtils.copyProperties(family, vo);
                vo.setId(family.getId().toString());
                voList.add(vo);
            });
        }
        return Response.ok(voList);
    }

    /**
     * 递归获取
     * 根据当前户主，获取上三代
     */
    private static List<UserFamilyDTO> createParentList(UserFamilyDTO familyHouseholder, List<UserFamilyDTO> list) {
        Integer num = 0;
        List<UserFamilyDTO> dtoList = list.stream().filter(model -> familyHouseholder.getRelation().equals(model.getUserId())).collect(Collectors.toList());
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
                    model.setChildren(createChildList(model, list));
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
    public Response<String> insertUserFamily(UserFamilyDTO userFamilyDTO) {
        UserFamily model = new UserFamily();
        BeanUtils.copyProperties(userFamilyDTO, model);
        if(StringUtils.isEmpty(userFamilyDTO.getFamilyId())){
            model.setFamilyId(IdWorker.getIdStr());
        }
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
            List<UserAddress> userAddressList = userAddressService.lambdaQuery()
                    .select(UserAddress::getUserId)
                    .like(StringUtils.isNotEmpty(userFamilyDTO.getNameNoPhoneAddress()), UserAddress::getUserId, userFamilyDTO.getNameNoPhoneAddress())
                    .list();
            if (userAddressList != null && userAddressList.size() > 0) {
                userAddressList.forEach(userAddress -> {
                    userIds.add(userAddress.getUserId());
                });
            }
            if (userIds.size() == 0) {
                userIds.add("fill");
            }
        }

        if (userFamilyDTO.getIds() != null && userFamilyDTO.getIds().size() > 0) {
            if (familyIds != null && familyIds.size() > 0){
                //取交集
                familyIds.retainAll(userFamilyDTO.getIds());
            } else{
                //否则全部查询
                familyIds.addAll(userFamilyDTO.getIds());
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
                .eq(UserFamily::getHouseholder, 1)
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
                //4.2、获取户主社区
                UserDwelling userDwelling = userDwellingService.lambdaQuery()
                        .eq(UserDwelling::getPrincipalId, family.getId())
                        .one();
                //4.3、获取户主地址
                UserAddress userAddress = userAddressService.lambdaQuery()
                        .eq(UserAddress::getUserId, family.getUserId())
                        .one();
                //4.4、设置属性
                UserFamilyVo entity = new UserFamilyVo().builder().build()
                        .setId(family.getId().toString())
                        .setFamilyId(family.getFamilyId())
                        .setPhone(userInstance.getPhone())
                        .setHouseholderName(userInstance.getName())
                        .setIdNo(userInstance.getIdNo())
                        .setCommunity(userDwelling.getCommunity())
                        .setResidential(userAddress.getAddress())
                        .setCommunity(userDwelling.getCommunity());
                voList.add(entity);
                // TODO 判断是否存在个人档案或者客户管理相关信息，如果有，则获取所属健管师

            });
        }
        BeanUtils.copyProperties(familyPage, pageVo, new String[]{"records"});
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
                .eq(UserFamily::getHouseholder, 0)
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
                UserInstance userInstance = userInstanceService.getById(Long.valueOf(family.getUserId()));
                if(userInstance != null) {
                    //4.2、设置属性
                    UserFamilyVo entity = UserFamilyVo.builder()
                            .memberName(userInstance.getName())
                            .id(userInstance.getId().toString())
                            .familyId(family.getFamilyId())
                            .idNo(userInstance.getIdNo())
                            .gender(userInstance.getGender())
                            .relation(family.getRelation()).build();
                    voList.add(entity);
                }
            });
        }
        BeanUtils.copyProperties(familyPage, pageVo, new String[]{"records"});
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

    @Override
    public Response<UserFamilyVo> checkUserIsFamilyHouseHolder(String userInstanceId) {
        UserFamily userFamily = userFamilyService.lambdaQuery()
                .eq(UserFamily::getUserId, userInstanceId)
                .eq(UserFamily::getHouseholder,true)
                .one();
        UserFamilyVo vo = new UserFamilyVo();
        if(userFamily != null) {
            //复制属性
            if (userFamily != null) {
                BeanUtils.copyProperties(userFamily, vo);
                vo.setId(userFamily.getId().toString());
            }
        }
        return Response.ok(vo);
    }
}
