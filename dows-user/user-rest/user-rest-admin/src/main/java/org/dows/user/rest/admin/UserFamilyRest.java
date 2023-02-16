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
import org.dows.user.entity.UserFamily;
import org.dows.user.entity.UserInstance;
import org.dows.user.enums.EnumUserStatusCode;
import org.dows.user.exception.UserException;
import org.dows.user.form.UserFamilyForm;
import org.dows.user.service.UserAddressService;
import org.dows.user.service.UserFamilyService;
import org.dows.user.util.ReflectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.nio.channels.AcceptPendingException;
import java.util.List;

/**
 * @author Administrator
 * @date 2023/2/2 15:09
 */
@Api(tags = "用户-家庭")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("userFamily")
public class UserFamilyRest implements MybatisCrudRest<UserFamilyForm, UserFamily, UserFamilyService> {
    private final UserFamilyApi userFamilyApi;
    private final UserInstanceApi userInstanceApi;
    private final UserExtinfoApi userExtinfoApi;
    private final UserCompanyApi userCompanyApi;
    private final UserEducationApi userEducationApi;
    private final UserJobApi userJobApi;
    private final UserDwellingApi userDwellingApi;
    private final UserAddressApi userAddressApi;

    @ApiOperation("查询 家庭-族谱")
    @GetMapping("/getGenealogyList/{id}")
    public Response<GenealogyDTO> getGenealogyList(@PathVariable("id") String id) {
        Response<GenealogyDTO> vo = userFamilyApi.getGenealogyList(id);
        return vo;
    }

    @ApiOperation("查询 用户-家庭-成员-列表")
    @PostMapping("/getFamilyMemberList")
    public Response<IPage<UserFamilyVo>> getFamilyMemberList(@RequestBody UserFamilyDTO userFamilyDTO) {
        Response<IPage<UserFamilyVo>> pageList = userFamilyApi.getFamilyMemberList(userFamilyDTO);
        return pageList;
    }

    @ApiOperation("新增 用户-家庭-成员")
    @PostMapping("/createFamilyMember")
    @Transactional(rollbackFor = Exception.class)
    public Response<String> createFamilyMember(@RequestBody UserFamilyDTO userFamilyDTO) {
        //1、校验该身份证号的用户是否已存在
        String userInstanceId = "";
        UserInstanceDTO userInstance = new UserInstanceDTO();
        userInstance.setIdNo(userFamilyDTO.getIdNo());
        List<UserInstanceVo> userInstanceVos = userInstanceApi.getUserInstanceListNoPage(userInstance).getData();
        if (userInstanceVos != null && userInstanceVos.size() > 0) {
            UserInstanceVo vo = userInstanceVos.get(0);
            //1.1、更新家庭成员实例
            UserInstanceDTO userInstanceDTO = new UserInstanceDTO();
            BeanUtils.copyProperties(userFamilyDTO, userInstanceDTO);
            userInstanceDTO.setName(userFamilyDTO.getMemberName());
            userInstanceDTO.setId(vo.getId());
            userInstanceId = userInstanceApi.updateUserInstance(userInstanceDTO).getData();
        } else {
            //1.2、新增家庭成员实例
            UserInstanceDTO userInstanceDTO = new UserInstanceDTO();
            BeanUtils.copyProperties(userFamilyDTO, userInstanceDTO);
            userInstanceDTO.setName(userFamilyDTO.getMemberName());
            userInstanceId = userInstanceApi.insertUserInstance(userInstanceDTO).getData();
        }
        //2、新增用户家庭
        //2.1、判断家庭中是否已存在该成员
        UserFamilyVo userFamilyVo = userFamilyApi.getUserFamilyByUserIdAndFamilyId(userInstanceId, userFamilyDTO.getFamilyId()).getData();
        if (userFamilyVo != null && !ReflectUtil.isObjectNull(userFamilyVo)) {
            throw new UserException(EnumUserStatusCode.FAMILY_USER_EXIST_EXCEPTION);
        }
        userFamilyDTO.setUserId(userInstanceId);
        userFamilyApi.insertUserFamily(userFamilyDTO).getData();
        //3、新增用户扩展信息
        UserExtinfoVo extinfoVo = userExtinfoApi.getUserExtinfoByUserId(userInstanceId).getData();
        if (extinfoVo != null && !ReflectUtil.isObjectNull(extinfoVo)) {
            UserExtinfoDTO userExtinfoDTO = new UserExtinfoDTO();
            BeanUtils.copyProperties(userFamilyDTO, userExtinfoDTO);
            userExtinfoDTO.setUserId(userInstanceId);
            userExtinfoDTO.setId(extinfoVo.getId());
            userExtinfoApi.updateUserExtinfoById(userExtinfoDTO);
        } else {
            UserExtinfoDTO userExtinfoDTO = new UserExtinfoDTO();
            BeanUtils.copyProperties(userFamilyDTO, userExtinfoDTO);
            userExtinfoDTO.setUserId(userInstanceId);
            userExtinfoApi.insertUserExtinfo(userExtinfoDTO);
        }
        //4、插入用户工作信息
        //4.1、判断是否已经存在该用户工作信息
        UserJobVo jobVo = userJobApi.getUserJobByUserId(userInstanceId).getData();
        if (jobVo != null && !ReflectUtil.isObjectNull(jobVo)) {
            UserJobDTO userJobDTO = new UserJobDTO();
            BeanUtils.copyProperties(userFamilyDTO, userJobDTO);
            userJobDTO.setUserId(userInstanceId);
            userJobDTO.setId(jobVo.getId());
            userJobApi.updateUserJobById(userJobDTO);
        } else {
            UserJobDTO userJobDTO = new UserJobDTO();
            BeanUtils.copyProperties(userFamilyDTO, userJobDTO);
            userJobDTO.setUserId(userInstanceId);
            userJobApi.insertUserJob(userJobDTO);
        }
        //5、插入用户公司信息
        //5.1、判断是否已经存在该用户公司信息
        UserCompanyVo companyVo = userCompanyApi.getUserCompanyByUserId(userInstanceId).getData();
        if (companyVo != null && !ReflectUtil.isObjectNull(companyVo)) {
            UserCompanyDTO userCompanyDTO = new UserCompanyDTO();
            BeanUtils.copyProperties(userFamilyDTO, userCompanyDTO);
            userCompanyDTO.setUserId(userInstanceId);
            userCompanyDTO.setId(companyVo.getId());
            userCompanyApi.updateUserCompanyById(userCompanyDTO);
        } else {
            UserCompanyDTO userCompanyDTO = new UserCompanyDTO();
            BeanUtils.copyProperties(userFamilyDTO, userCompanyDTO);
            userCompanyDTO.setUserId(userInstanceId);
            userCompanyApi.insertUserCompany(userCompanyDTO);
        }
        //6、插入用户教育信息
        //6.1、判断是否已经存在该用户教育信息
        UserEducationVo educationVo = userEducationApi.getUserEducationByUserId(userInstanceId).getData();
        if (educationVo != null && !ReflectUtil.isObjectNull(educationVo)) {
            UserEducationDTO userEducationDTO = new UserEducationDTO();
            BeanUtils.copyProperties(userFamilyDTO, userEducationDTO);
            userEducationDTO.setUserId(userInstanceId);
            userEducationDTO.setId(educationVo.getId());
            userEducationApi.updateUserEducationById(userEducationDTO);
        } else {
            UserEducationDTO userEducationDTO = new UserEducationDTO();
            BeanUtils.copyProperties(userFamilyDTO, userEducationDTO);
            userEducationDTO.setUserId(userInstanceId);
            userEducationApi.insertUserEducation(userEducationDTO);
        }
        return Response.ok(userInstanceId);
    }

    @ApiOperation("查询 用户-家庭-成员")
    @GetMapping("/getFamilyMemberById/{id}/{familyId}")
    public Response<UserFamilyVo> getFamilyMemberById(@PathVariable("id") String id, @PathVariable("familyId") String familyId) {
        UserFamilyVo familyVo = new UserFamilyVo();
        //1、获取家庭成员实例
        UserInstanceVo instanceVo = userInstanceApi.getUserInstanceById(id).getData();
        BeanUtils.copyProperties(instanceVo, familyVo);
        familyVo.setMemberName(instanceVo.getName());
        //2、获取用户家庭信息
        UserFamilyVo model = userFamilyApi.getUserFamilyByUserIdAndFamilyId(instanceVo.getId(), familyId).getData();
        familyVo.setRelation(model.getRelation());
        familyVo.setFamilyId(model.getFamilyId());
        //3、获取用户扩展信息
        UserExtinfoVo extinfoVo = userExtinfoApi.getUserExtinfoByUserId(instanceVo.getId()).getData();
        familyVo.setMarried(extinfoVo.getMarried());
        //4、获取用户工作信息
        UserJobVo jobVo = userJobApi.getUserJobByUserId(instanceVo.getId()).getData();
        familyVo.setProfession(jobVo.getProfession());
        //5、获取用户公司信息
        UserCompanyVo companyVo = userCompanyApi.getUserCompanyByUserId(instanceVo.getId()).getData();
        familyVo.setCompanyName(companyVo.getCompanyName());
        //6、获取用户教育信息
        UserEducationVo educationVo = userEducationApi.getUserEducationByUserId(instanceVo.getId()).getData();
        familyVo.setDegree(educationVo.getDegree());
        return Response.ok(familyVo);
    }


    @ApiOperation("编辑 用户-家庭-成员")
    @PostMapping("/updateFamilyMemberById")
    @Transactional(rollbackFor = Exception.class)
    public void updateFamilyMemberById(@RequestBody UserFamilyDTO userFamilyDTO) {
        //1、更新家庭成员实例
        UserInstanceDTO userInstanceDTO = new UserInstanceDTO();
        BeanUtils.copyProperties(userFamilyDTO, userInstanceDTO);
        userInstanceDTO.setName(userFamilyDTO.getMemberName());
        userInstanceApi.updateUserInstance(userInstanceDTO).getData();
        //2、更新用户家庭
        UserFamilyVo familyVo = userFamilyApi.getUserFamilyByUserIdAndFamilyId(userFamilyDTO.getId(), userFamilyDTO.getFamilyId()).getData();
        UserFamilyDTO model = new UserFamilyDTO();
        BeanUtils.copyProperties(userFamilyDTO, model, new String[]{"userId"});
        model.setId(familyVo.getId());
        userFamilyApi.updateUserFamilyById(model);
        //3、新增用户扩展信息
        UserExtinfoVo extinfoVo = userExtinfoApi.getUserExtinfoByUserId(userFamilyDTO.getId()).getData();
        UserExtinfoDTO userExtinfoDTO = new UserExtinfoDTO();
        BeanUtils.copyProperties(userFamilyDTO, userExtinfoDTO, new String[]{"userId"});
        userExtinfoDTO.setId(extinfoVo.getId());
        userExtinfoApi.updateUserExtinfoById(userExtinfoDTO);
        //4、更新用户工作信息
        UserJobVo jobVo = userJobApi.getUserJobByUserId(userFamilyDTO.getId()).getData();
        UserJobDTO userJobDTO = new UserJobDTO();
        BeanUtils.copyProperties(userFamilyDTO, userJobDTO, new String[]{"userId"});
        userJobDTO.setId(jobVo.getId());
        userJobApi.updateUserJobById(userJobDTO);
        //5、更新用户公司信息
        UserCompanyVo companyVo = userCompanyApi.getUserCompanyByUserId(userFamilyDTO.getId()).getData();
        UserCompanyDTO userCompanyDTO = new UserCompanyDTO();
        BeanUtils.copyProperties(userFamilyDTO, userCompanyDTO, new String[]{"userId"});
        userCompanyDTO.setId(companyVo.getId());
        userCompanyApi.updateUserCompanyById(userCompanyDTO);
        //6、更新用户教育信息
        UserEducationVo educationVo = userEducationApi.getUserEducationByUserId(userFamilyDTO.getId()).getData();
        UserEducationDTO userEducationDTO = new UserEducationDTO();
        BeanUtils.copyProperties(userFamilyDTO, userEducationDTO, new String[]{"userId"});
        userEducationDTO.setId(educationVo.getId());
        userEducationApi.updateUserEducationById(userEducationDTO);
    }

    @ApiOperation("删除 用户-家庭-成员")
    @DeleteMapping("/deleteFamilyMemberById/{id}/{familyId}")
    @Transactional(rollbackFor = Exception.class)
    public void deleteFamilyMemberById(@PathVariable("id") String id, @PathVariable("familyId") String familyId) {
        //1、删除家庭成员实例
        userInstanceApi.deleteUserInstanceById(id);
        //2、删除用户家庭
        UserFamilyVo familyVo = userFamilyApi.getUserFamilyByUserIdAndFamilyId(id, familyId).getData();
        userFamilyApi.deleteUserFamilyById(familyVo.getId());
        //3、删除用户扩展信息
        UserExtinfoVo extinfoVo = userExtinfoApi.getUserExtinfoByUserId(id).getData();
        userExtinfoApi.deleteUserExtinfoById(extinfoVo.getId());
        //4、删除用户工作信息
        UserJobVo jobVo = userJobApi.getUserJobByUserId(id).getData();
        userJobApi.deleteUserJobById(jobVo.getId());
        //5、删除用户公司信息
        UserCompanyVo companyVo = userCompanyApi.getUserCompanyByUserId(id).getData();
        userCompanyApi.deleteUserCompanyById(companyVo.getId());
        //6、删除用户教育信息
        UserEducationVo educationVo = userEducationApi.getUserEducationByUserId(id).getData();
        userEducationApi.deleteUserEducationById(educationVo.getId());
    }

    @ApiOperation("批量删除 用户-家庭-成员")
    @DeleteMapping("/batchDeleteFamilyMembers")
    @Transactional(rollbackFor = Exception.class)
    public void batchDeleteFamilyMembers(@RequestParam("ids") List<String> ids, @RequestParam("familyId") String familyId) {
        ids.forEach(id -> {
            //1、删除家庭成员实例
            userInstanceApi.deleteUserInstanceById(id);
            //2、删除用户家庭
            UserFamilyVo familyVo = userFamilyApi.getUserFamilyByUserIdAndFamilyId(id, familyId).getData();
            userFamilyApi.deleteUserFamilyById(familyVo.getId());
            //3、删除用户扩展信息
            UserExtinfoVo extinfoVo = userExtinfoApi.getUserExtinfoByUserId(id).getData();
            userExtinfoApi.deleteUserExtinfoById(extinfoVo.getId());
            //4、删除用户工作信息
            UserJobVo jobVo = userJobApi.getUserJobByUserId(id).getData();
            userJobApi.deleteUserJobById(jobVo.getId());
            //5、删除用户公司信息
            UserCompanyVo companyVo = userCompanyApi.getUserCompanyByUserId(id).getData();
            userCompanyApi.deleteUserCompanyById(companyVo.getId());
            //6、删除用户教育信息
            UserEducationVo educationVo = userEducationApi.getUserEducationByUserId(id).getData();
            userEducationApi.deleteUserEducationById(educationVo.getId());
        });
    }

    @ApiOperation("查询 用户-家庭-户主-列表")
    @PostMapping("/getFamilyArchivesList")
    public Response<IPage<UserFamilyVo>> getFamilyArchivesList(@RequestBody UserFamilyDTO userFamilyDTO) {
        Response<IPage<UserFamilyVo>> pageList = userFamilyApi.getFamilyArchivesList(userFamilyDTO);
        return pageList;
    }

    @ApiOperation("新增 用户-家庭-信息")
    @PostMapping("/insertUserFamily")
    @Transactional(rollbackFor = Exception.class)
    public Response<String> insertUserFamily(@RequestBody UserFamilyDTO userFamilyDTO) {
        //1、校验该身份证号的用户是否已存在
        String userInstanceId = "";
        UserInstanceDTO userInstance = new UserInstanceDTO();
        userInstance.setIdNo(userFamilyDTO.getIdNo());
        List<UserInstanceVo> userInstanceVos = userInstanceApi.getUserInstanceListNoPage(userInstance).getData();
        if (userInstanceVos != null && userInstanceVos.size() > 0) {
            UserInstanceVo vo = userInstanceVos.get(0);
            //1.1、更新家庭户主实例
            UserInstanceDTO userInstanceDTO = new UserInstanceDTO();
            BeanUtils.copyProperties(userFamilyDTO, userInstanceDTO);
            userInstanceDTO.setName(userFamilyDTO.getHouseholderName());
            userInstanceDTO.setId(vo.getId());
            userInstanceId = userInstanceApi.updateUserInstance(userInstanceDTO).getData();
        } else {
            //1.2、新增家庭户主实例
            UserInstanceDTO userInstanceDTO = new UserInstanceDTO();
            BeanUtils.copyProperties(userFamilyDTO, userInstanceDTO);
            userInstanceDTO.setName(userFamilyDTO.getHouseholderName());
            userInstanceId = userInstanceApi.insertUserInstance(userInstanceDTO).getData();
        }
        //2、新增用户家庭
        userFamilyDTO.setUserId(userInstanceId);
        String familyId = userFamilyApi.insertUserFamily(userFamilyDTO).getData();
        //3、新增用户扩展信息
        //3.1、判断是否已经存在该用户扩展信息
        UserExtinfoVo extinfoVo = userExtinfoApi.getUserExtinfoByUserId(userInstanceId).getData();
        if (extinfoVo != null && !ReflectUtil.isObjectNull(extinfoVo)) {
            UserExtinfoDTO userExtinfoDTO = new UserExtinfoDTO();
            BeanUtils.copyProperties(userFamilyDTO, userExtinfoDTO);
            userExtinfoDTO.setUserId(userInstanceId);
            userExtinfoDTO.setId(extinfoVo.getId());
            userExtinfoApi.updateUserExtinfoById(userExtinfoDTO);
        } else {
            UserExtinfoDTO userExtinfoDTO = new UserExtinfoDTO();
            BeanUtils.copyProperties(userFamilyDTO, userExtinfoDTO);
            userExtinfoDTO.setUserId(userInstanceId);
            userExtinfoApi.insertUserExtinfo(userExtinfoDTO);
        }
        //4、插入用户公司信息
        //4.1、判断是否已经存在该用户公司信息
        UserCompanyVo companyVo = userCompanyApi.getUserCompanyByUserId(userInstanceId).getData();
        if (companyVo != null && !ReflectUtil.isObjectNull(companyVo)) {
            UserCompanyDTO userCompanyDTO = new UserCompanyDTO();
            BeanUtils.copyProperties(userFamilyDTO, userCompanyDTO);
            userCompanyDTO.setUserId(userInstanceId);
            userCompanyDTO.setId(companyVo.getId());
            userCompanyApi.updateUserCompanyById(userCompanyDTO);
        } else {
            UserCompanyDTO userCompanyDTO = new UserCompanyDTO();
            BeanUtils.copyProperties(userFamilyDTO, userCompanyDTO);
            userCompanyDTO.setUserId(userInstanceId);
            userCompanyApi.insertUserCompany(userCompanyDTO);
        }
        //5、插入用户教育信息
        //5.1、判断是否已经存在该用户教育信息
        UserEducationVo educationVo = userEducationApi.getUserEducationByUserId(userInstanceId).getData();
        if (educationVo != null && !ReflectUtil.isObjectNull(educationVo)) {
            UserEducationDTO userEducationDTO = new UserEducationDTO();
            BeanUtils.copyProperties(userFamilyDTO, userEducationDTO);
            userEducationDTO.setUserId(userInstanceId);
            userEducationDTO.setId(educationVo.getId());
            userEducationApi.updateUserEducationById(userEducationDTO);
        } else {
            UserEducationDTO userEducationDTO = new UserEducationDTO();
            BeanUtils.copyProperties(userFamilyDTO, userEducationDTO);
            userEducationDTO.setUserId(userInstanceId);
            userEducationApi.insertUserEducation(userEducationDTO);
        }
        //6、插入用户工作信息
        //6.1、判断是否已经存在该用户工作信息
        UserJobVo jobVo = userJobApi.getUserJobByUserId(userInstanceId).getData();
        if (jobVo != null && !ReflectUtil.isObjectNull(jobVo)) {
            UserJobDTO userJobDTO = new UserJobDTO();
            BeanUtils.copyProperties(userFamilyDTO, userJobDTO);
            userJobDTO.setUserId(userInstanceId);
            userJobDTO.setId(jobVo.getId());
            userJobApi.updateUserJobById(userJobDTO);
        } else {
            UserJobDTO userJobDTO = new UserJobDTO();
            BeanUtils.copyProperties(userFamilyDTO, userJobDTO);
            userJobDTO.setUserId(userInstanceId);
            userJobApi.insertUserJob(userJobDTO);
        }
        //7、插入用户住所信息
        UserDwellingDTO userDwellingDTO = new UserDwellingDTO();
        BeanUtils.copyProperties(userFamilyDTO, userDwellingDTO);
        userDwellingDTO.setFamilyId(familyId);
        userDwellingApi.insertUserDwelling(userDwellingDTO);
        //8、插入用户住址
        UserAddressVo addressVo = userAddressApi.getUserAddressByUserId(userInstanceId).getData();
        if (addressVo != null && !ReflectUtil.isObjectNull(addressVo)) {
            UserAddressDTO userAddressDTO = new UserAddressDTO();
            BeanUtils.copyProperties(userFamilyDTO, userAddressDTO);
            userAddressDTO.setUserId(userInstanceId);
            userAddressDTO.setId(addressVo.getId());
            userAddressApi.updateUserAddressById(userAddressDTO);
        } else {
            UserAddressDTO userAddressDTO = new UserAddressDTO();
            BeanUtils.copyProperties(userFamilyDTO, userAddressDTO);
            userAddressDTO.setUserId(userInstanceId);
            userAddressApi.insertUserAddress(userAddressDTO);
        }
        return Response.ok(familyId);
    }


    @ApiOperation("查看 用户-家庭-信息")
    @GetMapping("/getUserFamilyById/{id}")
    @Transactional(rollbackFor = Exception.class)
    public Response<UserFamilyVo> getUserFamilyById(@PathVariable("id") String id) {
        UserFamilyVo familyVo = new UserFamilyVo();
        //1、获取用户家庭信息
        UserFamilyVo model = userFamilyApi.getUserFamilyById(id).getData();
        if (!model.getHouseholder()) {
            throw new UserException(EnumUserStatusCode.USER_IS_NOT_HOUSEHOLDER_EXCEPTION);
        }
        BeanUtils.copyProperties(model, familyVo);
        //2、获取用户实例
        UserInstanceVo instanceVo = userInstanceApi.getUserInstanceById(model.getUserId()).getData();
        BeanUtils.copyProperties(instanceVo, familyVo, new String[]{"id", "userId"});
        familyVo.setHouseholderName(instanceVo.getName());
        //3、获取用户扩展信息
        UserExtinfoVo extinfoVo = userExtinfoApi.getUserExtinfoByUserId(model.getUserId()).getData();
        BeanUtils.copyProperties(extinfoVo, familyVo, new String[]{"id", "userId"});
        //4、获取用户公司信息
        UserCompanyVo companyVo = userCompanyApi.getUserCompanyByUserId(model.getUserId()).getData();
        BeanUtils.copyProperties(companyVo, familyVo, new String[]{"id", "userId"});
        //5、获取用户教育信息
        UserEducationVo educationVo = userEducationApi.getUserEducationByUserId(model.getUserId()).getData();
        BeanUtils.copyProperties(educationVo, familyVo, new String[]{"id", "userId"});
        //6、获取用户工作信息
        UserJobVo jobVo = userJobApi.getUserJobByUserId(model.getUserId()).getData();
        BeanUtils.copyProperties(jobVo, familyVo, new String[]{"id", "userId"});
        //7、获取用户住所信息
        UserDwellingVo dwellingVo = userDwellingApi.getUserDwellingByFamilyId(id).getData();
        BeanUtils.copyProperties(dwellingVo, familyVo, new String[]{"id", "familyId"});
        //8、获取用户地址信息
        UserAddressVo addressVo = userAddressApi.getUserAddressByUserId(model.getUserId()).getData();
        BeanUtils.copyProperties(addressVo, familyVo, new String[]{"id", "userId"});
        return Response.ok(familyVo);
    }

    @ApiOperation("编辑 用户-家庭")
    @PostMapping("/updateUserFamilyById")
    @Transactional(rollbackFor = Exception.class)
    public void updateUserFamilyById(@RequestBody UserFamilyDTO userFamilyDTO) {
        //1、获取用户家庭信息
        UserFamilyVo model = userFamilyApi.getUserFamilyById(userFamilyDTO.getId()).getData();
        if (!model.getHouseholder()) {
            throw new UserException(EnumUserStatusCode.USER_IS_NOT_HOUSEHOLDER_EXCEPTION);
        }
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
        UserDwellingVo dwellingVo = userDwellingApi.getUserDwellingByFamilyId(model.getId()).getData();
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
    }


    @ApiOperation("删除单个 用户-家庭-信息")
    @DeleteMapping("/deleteUserFamilyById/{id}")
    public void deleteUserFamilyById(@PathVariable("id") String id) {
        //1、删除家庭实例
        UserFamilyVo familyVo = userFamilyApi.getUserFamilyById(id).getData();
        userFamilyApi.deleteUserFamilyById(id);
        //2、删除用户实例
        UserInstanceVo instanceVo = userInstanceApi.getUserInstanceById(familyVo.getUserId()).getData();
        userInstanceApi.deleteUserInstanceById(instanceVo.getId());
        //3、删除用户扩展信息
        UserExtinfoVo extinfoVo = userExtinfoApi.getUserExtinfoByUserId(familyVo.getUserId()).getData();
        userExtinfoApi.deleteUserExtinfoById(extinfoVo.getId());
        //4、删除用户工作信息
        UserJobVo jobVo = userJobApi.getUserJobByUserId(familyVo.getUserId()).getData();
        userJobApi.deleteUserJobById(jobVo.getId());
        //5、删除用户公司信息
        UserCompanyVo companyVo = userCompanyApi.getUserCompanyByUserId(familyVo.getUserId()).getData();
        userCompanyApi.deleteUserCompanyById(companyVo.getId());
        //6、删除用户教育信息
        UserEducationVo educationVo = userEducationApi.getUserEducationByUserId(familyVo.getUserId()).getData();
        userEducationApi.deleteUserEducationById(educationVo.getId());
        //7、删除用户做所信息
        UserDwellingVo dwellingVo = userDwellingApi.getUserDwellingByFamilyId(familyVo.getId()).getData();
        userDwellingApi.deleteUserDwellingById(dwellingVo.getId());
        //8、删除用户地址信息
        UserAddressVo addressVo = userAddressApi.getUserAddressByUserId(familyVo.getUserId()).getData();
        userAddressApi.deleteUserAddressById(addressVo.getId());
    }

    @ApiOperation("批量删除 用户-家庭")
    @DeleteMapping("/batchDeleteUserFamilys")
    public void batchDeleteUserFamilys(@RequestParam("ids") List<String> ids) {
        ids.forEach(id -> {
            //1、删除家庭实例
            UserFamilyVo familyVo = userFamilyApi.getUserFamilyById(id).getData();
            userFamilyApi.deleteUserFamilyById(id);
            //2、删除用户实例
            UserInstanceVo instanceVo = userInstanceApi.getUserInstanceById(familyVo.getUserId()).getData();
            userInstanceApi.deleteUserInstanceById(instanceVo.getId());
            //3、删除用户扩展信息
            UserExtinfoVo extinfoVo = userExtinfoApi.getUserExtinfoByUserId(familyVo.getUserId()).getData();
            userExtinfoApi.deleteUserExtinfoById(extinfoVo.getId());
            //4、删除用户工作信息
            UserJobVo jobVo = userJobApi.getUserJobByUserId(familyVo.getUserId()).getData();
            userJobApi.deleteUserJobById(jobVo.getId());
            //5、删除用户公司信息
            UserCompanyVo companyVo = userCompanyApi.getUserCompanyByUserId(familyVo.getUserId()).getData();
            userCompanyApi.deleteUserCompanyById(companyVo.getId());
            //6、删除用户教育信息
            UserEducationVo educationVo = userEducationApi.getUserEducationByUserId(familyVo.getUserId()).getData();
            userEducationApi.deleteUserEducationById(educationVo.getId());
            //7、删除用户做所信息
            UserDwellingVo dwellingVo = userDwellingApi.getUserDwellingByFamilyId(familyVo.getId()).getData();
            userDwellingApi.deleteUserDwellingById(dwellingVo.getId());
            //8、删除用户地址信息
            UserAddressVo addressVo = userAddressApi.getUserAddressByUserId(familyVo.getUserId()).getData();
            userAddressApi.deleteUserAddressById(addressVo.getId());
        });
    }
}
