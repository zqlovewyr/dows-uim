package org.dows.user.rest.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.Response;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.user.api.api.*;
import org.dows.user.api.dto.*;
import org.dows.user.api.vo.*;
import org.dows.user.constant.BaseConstant;
import org.dows.user.entity.UserInstance;
import org.dows.user.enums.EnumUserStatusCode;
import org.dows.user.exception.UserException;
import org.dows.user.service.UserInstanceService;
import org.dows.user.form.UserInstanceForm;
import org.dows.user.util.ReflectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户-实例(UserInstance)表控制层
 *
 * @author lait.zhang
 * @since 2022-10-22 10:31:13
 */
@Api(tags = "用户-实例")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("userInstance")
public class UserInstanceRest implements MybatisCrudRest<UserInstanceForm, UserInstance, UserInstanceService> {

    private final UserInstanceApi userInstanceApi;

    private final UserExtinfoApi userExtinfoApi;

    private final UserCompanyApi userCompanyApi;

    private final UserEducationApi userEducationApi;

    private final UserJobApi userJobApi;

    private final UserDwellingApi userDwellingApi;

    private final UserAddressApi userAddressApi;

    private final UserContactApi userContactApi;


    @ApiOperation("查看 用户-实例-列表")
    @PostMapping("/customUserInstanceList")
    public Response<IPage<UserInstanceVo>> customUserInstanceList(@RequestBody UserInstanceDTO userInstanceDTO) {
        return userInstanceApi.userInstanceUnionList(userInstanceDTO);
    }

    @ApiOperation("新增 用户-实例")
    @PostMapping("/insertUserInstance")
    public Response<String> insertUserInstance(@RequestBody UserInstanceDTO userInstanceDTO) {
        return userInstanceApi.insertUserInstance(userInstanceDTO);
    }

    @ApiOperation("更新 用户-实例")
    @PutMapping("/updateUserInstance")
    public Response<String> updateUserInstance(@RequestBody UserInstanceDTO userInstanceDTO) {
        return userInstanceApi.updateUserInstance(userInstanceDTO);
    }

    @ApiOperation("查看 用户-实例")
    @GetMapping("/getUserInstanceById/{id}")
    public Response<UserInstanceVo> getUserInstanceById(@PathVariable("id") String id) {
        return userInstanceApi.getUserInstanceById(id);
    }

    @ApiOperation("删除单个 用户-实例")
    @DeleteMapping("/deleteUserInstanceById/{id}")
    public Response deleteUserInstanceById(@PathVariable("id") String id) {
        Response<Boolean> flag = userInstanceApi.deleteUserInstanceById(id);
        return Response.ok(flag);
    }

    @ApiOperation("批量删除 用户-实例")
    @DeleteMapping("/deleteUserInstances")
    public void deleteUserInstances(@RequestParam("ids") List<String> ids) {
        userInstanceApi.deleteUserInstances(ids);
    }

    @ApiOperation("查询 用户-实例 列表(不带分页)")
    @PostMapping("/getUserInstanceList")
    public Response<List<UserInstanceVo>> getUserInstanceList(@RequestBody UserInstanceDTO userInstanceDTO) {
        return userInstanceApi.getUserInstanceListNoPage(userInstanceDTO);
    }

    @ApiOperation("新增 个人-档案-基本信息")
    @PostMapping("/insertUserProfile")
    @Transactional(rollbackFor = Exception.class)
    public Response<String> insertUserProfile(@RequestBody UserProfileDTO userProfileDTO) {
        //1、校验该身份证号的用户是否已存在
        String userInstanceId = "";
        UserInstanceDTO userInstance = new UserInstanceDTO();
        userInstance.setIdNo(userProfileDTO.getIdNo());
        List<UserInstanceVo> userInstanceVos = userInstanceApi.getUserInstanceListNoPage(userInstance).getData();
        if (userInstanceVos != null && userInstanceVos.size() > 0) {
            UserInstanceVo vo = userInstanceVos.get(0);
            //1.1、更新个人档案实例
            UserInstanceDTO userInstanceDTO = new UserInstanceDTO();
            BeanUtils.copyProperties(userProfileDTO, userInstanceDTO);
            userInstanceDTO.setId(vo.getId());
            userInstanceId = userInstanceApi.updateUserInstance(userInstanceDTO).getData();
        } else {
            //1.2、新增个人档案实例
            UserInstanceDTO userInstanceDTO = new UserInstanceDTO();
            BeanUtils.copyProperties(userProfileDTO, userInstanceDTO);
            userInstanceId = userInstanceApi.insertUserInstance(userInstanceDTO).getData();
        }
        //2、新增用户扩展信息
        //2.1、判断是否已经存在该用户扩展信息
        UserExtinfoVo extinfoVo = userExtinfoApi.getUserExtinfoByUserId(userInstanceId).getData();
        if (extinfoVo != null && !ReflectUtil.isObjectNull(extinfoVo)) {
            UserExtinfoDTO userExtinfoDTO = new UserExtinfoDTO();
            BeanUtils.copyProperties(userProfileDTO, userExtinfoDTO);
            userExtinfoDTO.setUserId(userInstanceId);
            userExtinfoDTO.setId(extinfoVo.getId());
            userExtinfoApi.updateUserExtinfoById(userExtinfoDTO);
        } else {
            UserExtinfoDTO userExtinfoDTO = new UserExtinfoDTO();
            BeanUtils.copyProperties(userProfileDTO, userExtinfoDTO);
            userExtinfoDTO.setUserId(userInstanceId);
            userExtinfoApi.insertUserExtinfo(userExtinfoDTO);
        }
        //3、插入用户公司信息
        //3.1、判断是否已经存在该用户公司信息
        UserCompanyVo companyVo = userCompanyApi.getUserCompanyByUserId(userInstanceId).getData();
        if (companyVo != null && !ReflectUtil.isObjectNull(companyVo)) {
            UserCompanyDTO userCompanyDTO = new UserCompanyDTO();
            BeanUtils.copyProperties(userProfileDTO, userCompanyDTO);
            userCompanyDTO.setUserId(userInstanceId);
            userCompanyDTO.setId(companyVo.getId());
            userCompanyApi.updateUserCompanyById(userCompanyDTO);
        } else {
            UserCompanyDTO userCompanyDTO = new UserCompanyDTO();
            BeanUtils.copyProperties(userProfileDTO, userCompanyDTO);
            userCompanyDTO.setUserId(userInstanceId);
            userCompanyApi.insertUserCompany(userCompanyDTO);
        }
        //4、插入用户教育信息
        //4.1、判断是否已经存在该用户教育信息
        UserEducationVo educationVo = userEducationApi.getUserEducationByUserId(userInstanceId).getData();
        if (educationVo != null && !ReflectUtil.isObjectNull(educationVo)) {
            UserEducationDTO userEducationDTO = new UserEducationDTO();
            BeanUtils.copyProperties(userProfileDTO, userEducationDTO);
            userEducationDTO.setUserId(userInstanceId);
            userEducationDTO.setId(educationVo.getId());
            userEducationApi.updateUserEducationById(userEducationDTO);
        } else {
            UserEducationDTO userEducationDTO = new UserEducationDTO();
            BeanUtils.copyProperties(userProfileDTO, userEducationDTO);
            userEducationDTO.setUserId(userInstanceId);
            userEducationApi.insertUserEducation(userEducationDTO);
        }
        //5、插入用户工作信息
        //5.1、判断是否已经存在该用户工作信息
        UserJobVo jobVo = userJobApi.getUserJobByUserId(userInstanceId).getData();
        if (jobVo != null && !ReflectUtil.isObjectNull(jobVo)) {
            UserJobDTO userJobDTO = new UserJobDTO();
            BeanUtils.copyProperties(userProfileDTO, userJobDTO);
            userJobDTO.setUserId(userInstanceId);
            userJobDTO.setId(jobVo.getId());
            userJobApi.updateUserJobById(userJobDTO);
        } else {
            UserJobDTO userJobDTO = new UserJobDTO();
            BeanUtils.copyProperties(userProfileDTO, userJobDTO);
            userJobDTO.setUserId(userInstanceId);
            userJobApi.insertUserJob(userJobDTO);
        }
        //6、插入用户住所信息
        UserDwellingVo dwellingVo = userDwellingApi.getUserDwellingByPrincipalId(userInstanceId).getData();
        if (dwellingVo != null && !ReflectUtil.isObjectNull(dwellingVo)) {
            UserDwellingDTO userDwellingDTO = new UserDwellingDTO();
            BeanUtils.copyProperties(userProfileDTO, userDwellingDTO);
            userDwellingDTO.setPrincipalId(userInstanceId);
            userDwellingDTO.setPrincipalType(BaseConstant.PERSONAL);
            userDwellingDTO.setId(dwellingVo.getId());
            userDwellingApi.updateUserDwellingById(userDwellingDTO);
        } else {
            UserDwellingDTO userDwellingDTO = new UserDwellingDTO();
            BeanUtils.copyProperties(userProfileDTO, userDwellingDTO);
            userDwellingDTO.setPrincipalId(userInstanceId);
            userDwellingDTO.setPrincipalType(BaseConstant.PERSONAL);
            userDwellingApi.insertUserDwelling(userDwellingDTO);
        }
        //7、插入用户住址
        UserAddressVo addressVo = userAddressApi.getUserAddressByUserId(userInstanceId).getData();
        if (addressVo != null && !ReflectUtil.isObjectNull(addressVo)) {
            UserAddressDTO userAddressDTO = new UserAddressDTO();
            BeanUtils.copyProperties(userProfileDTO, userAddressDTO);
            userAddressDTO.setUserId(userInstanceId);
            userAddressDTO.setId(addressVo.getId());
            userAddressApi.updateUserAddressById(userAddressDTO);
        } else {
            UserAddressDTO userAddressDTO = new UserAddressDTO();
            BeanUtils.copyProperties(userProfileDTO, userAddressDTO);
            userAddressDTO.setUserId(userInstanceId);
            userAddressApi.insertUserAddress(userAddressDTO);
        }
        //8、插入用户联系人信息
        UserContactVo contactVo = userContactApi.getUserContactByUserId(userInstanceId).getData();
        if (contactVo != null && !ReflectUtil.isObjectNull(contactVo)) {
            UserContactDTO userContactDTO = new UserContactDTO();
            BeanUtils.copyProperties(userProfileDTO, userContactDTO);
            userContactDTO.setUserId(userInstanceId);
            userContactDTO.setId(contactVo.getId());
            userContactApi.updateUserContactById(userContactDTO);
        } else {
            UserContactDTO userContactDTO = new UserContactDTO();
            BeanUtils.copyProperties(userProfileDTO, userContactDTO);
            userContactDTO.setUserId(userInstanceId);
            userContactApi.insertUserContact(userContactDTO);
        }
        return Response.ok(userInstanceId);
    }

/*    @ApiOperation("编辑 个人-档案-基本信息")
    @PostMapping("/updateUserProfileById")
    @Transactional(rollbackFor = Exception.class)
    public void updateUserFamilyById(@RequestBody UserProfileDTO userProfileDTO) {
        //2、更新家庭户主实例
        UserInstanceVo instanceVo = userInstanceApi.getUserInstanceById(model.getUserId()).getData();
        UserInstanceDTO userInstanceDTO = new UserInstanceDTO();
        BeanUtils.copyProperties(userFamilyDTO, userInstanceDTO, new String[]{"id"});
        userInstanceDTO.setId(instanceVo.getId());
        userInstanceDTO.setName(userFamilyDTO.getHouseholderName());
        userInstanceApi.updateUserInstance(userInstanceDTO);
        //3、更新用户扩展信息
        UserExtinfoVo extinfoVo = userExtinfoApi.getUserExtinfoByUserId(model.getUserId()).getData();
        UserExtinfoDTO userExtinfoDTO = new UserExtinfoDTO();
        BeanUtils.copyProperties(userFamilyDTO, userExtinfoDTO, new String[]{"id"});
        userExtinfoDTO.setId(extinfoVo.getId());
        userExtinfoApi.updateUserExtinfoById(userExtinfoDTO);
        //4、更新用户公司信息
        UserCompanyVo companyVo = userCompanyApi.getUserCompanyByUserId(model.getUserId()).getData();
        UserCompanyDTO userCompanyDTO = new UserCompanyDTO();
        BeanUtils.copyProperties(userFamilyDTO, userCompanyDTO, new String[]{"id"});
        userCompanyDTO.setId(companyVo.getId());
        userCompanyApi.updateUserCompanyById(userCompanyDTO);
        //5、更新用户教育信息
        UserEducationVo educationVo = userEducationApi.getUserEducationByUserId(model.getUserId()).getData();
        UserEducationDTO userEducationDTO = new UserEducationDTO();
        BeanUtils.copyProperties(userFamilyDTO, userEducationDTO, new String[]{"id"});
        userEducationDTO.setId(educationVo.getId());
        userEducationApi.updateUserEducationById(userEducationDTO);
        //6、更新用户工作信息
        UserJobVo jobVo = userJobApi.getUserJobByUserId(model.getUserId()).getData();
        UserJobDTO userJobDTO = new UserJobDTO();
        BeanUtils.copyProperties(userFamilyDTO, userJobDTO, new String[]{"id"});
        userJobDTO.setId(jobVo.getId());
        userJobApi.updateUserJobById(userJobDTO);
        //7、更新用户住所信息
        UserDwellingVo dwellingVo = userDwellingApi.getUserDwellingByPrincipalId(model.getId()).getData();
        UserDwellingDTO userDwellingDTO = new UserDwellingDTO();
        BeanUtils.copyProperties(userFamilyDTO, userDwellingDTO, new String[]{"id"});
        userDwellingDTO.setId(dwellingVo.getId());
        userDwellingDTO.setFamilyId(userFamilyDTO.getId());
        userDwellingApi.updateUserDwellingById(userDwellingDTO);
        //8、更新用户地址信息
        UserAddressVo addressVo = userAddressApi.getUserAddressByUserId(model.getUserId()).getData();
        UserAddressDTO userAddressDTO = new UserAddressDTO();
        BeanUtils.copyProperties(userFamilyDTO, userAddressDTO, new String[]{"id"});
        userAddressDTO.setId(addressVo.getId());
        userAddressApi.updateUserAddressById(userAddressDTO);
    }*/

    @ApiOperation("查看 个人-档案-基本信息")
    @GetMapping("/getUserProfileById/{id}")
    @Transactional(rollbackFor = Exception.class)
    public Response<UserProfileVo> getUserProfileById(@PathVariable("id") String id) {
        UserProfileVo vo = new UserProfileVo();
        //1、获取用户实例
        UserInstanceVo instanceVo = userInstanceApi.getUserInstanceById(id).getData();
        BeanUtils.copyProperties(instanceVo, vo, new String[]{"id", "userId"});
        //2、获取用户扩展信息
        UserExtinfoVo extinfoVo = userExtinfoApi.getUserExtinfoByUserId(id).getData();
        BeanUtils.copyProperties(extinfoVo, vo, new String[]{"id", "userId"});
        //3、获取用户公司信息
        UserCompanyVo companyVo = userCompanyApi.getUserCompanyByUserId(id).getData();
        BeanUtils.copyProperties(companyVo, vo, new String[]{"id", "userId"});
        //5、获取用户教育信息
        UserEducationVo educationVo = userEducationApi.getUserEducationByUserId(id).getData();
        BeanUtils.copyProperties(educationVo, vo, new String[]{"id", "userId"});
        //6、获取用户工作信息
        UserJobVo jobVo = userJobApi.getUserJobByUserId(id).getData();
        BeanUtils.copyProperties(jobVo, vo, new String[]{"id", "userId"});
        //7、获取用户住所信息
        UserDwellingVo dwellingVo = userDwellingApi.getUserDwellingByPrincipalId(id).getData();
        BeanUtils.copyProperties(dwellingVo, vo, new String[]{"id", "familyId"});
        //8、获取用户地址信息
        UserAddressVo addressVo = userAddressApi.getUserAddressByUserId(id).getData();
        BeanUtils.copyProperties(addressVo, vo, new String[]{"id", "userId"});
        return Response.ok(vo);
    }
}

