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
import org.dows.user.service.UserFamilyService;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("查询 家庭-族谱")
    @GetMapping("/getGenealogyList/{id}")
    public Response<UserFamilyVo> getGenealogyList(@PathVariable("id") Long id) {
        Response<UserFamilyVo> vo = userFamilyApi.getGenealogyList(id.toString());
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
    public Response<String> createFamilyMember(@RequestBody UserFamilyDTO userFamilyDTO) {
        //1、新增家庭成员实例
        UserInstanceDTO userInstanceDTO = new UserInstanceDTO();
        BeanUtils.copyProperties(userFamilyDTO, userInstanceDTO);
        String userInstanceId = userInstanceApi.insertUserInstance(userInstanceDTO).getData();
        //2、新增用户家庭
        userFamilyDTO.setUserId(userInstanceId);
        String familyId = userFamilyApi.insertUserFamily(userFamilyDTO).getData();
        //3、新增用户扩展信息
        UserExtinfoDTO userExtinfoDTO = new UserExtinfoDTO();
        BeanUtils.copyProperties(userFamilyDTO, userExtinfoDTO);
        userExtinfoDTO.setUserId(userInstanceId);
        userExtinfoApi.insertUserExtinfo(userExtinfoDTO);
        //4、插入用户工作信息
        UserJobDTO userJobDTO = new UserJobDTO();
        BeanUtils.copyProperties(userFamilyDTO, userJobDTO);
        userJobDTO.setUserId(userInstanceId);
        userJobApi.insertUserJob(userJobDTO);
        //5、插入用户公司信息
        UserCompanyDTO userCompanyDTO = new UserCompanyDTO();
        BeanUtils.copyProperties(userFamilyDTO, userCompanyDTO);
        userCompanyDTO.setUserId(userInstanceId);
        userCompanyApi.insertUserCompany(userCompanyDTO);
        //6、插入用户教育信息
        UserEducationDTO userEducationDTO = new UserEducationDTO();
        BeanUtils.copyProperties(userFamilyDTO, userEducationDTO);
        userEducationDTO.setUserId(userInstanceId);
        userEducationApi.insertUserEducation(userEducationDTO);
        return Response.ok(userInstanceId);
    }

    @ApiOperation("查询 用户-家庭-成员")
    @GetMapping("/getFamilyMemberById/{id}")
    public Response<UserFamilyVo> getFamilyMemberById(@PathVariable("id") String id) {
        UserFamilyVo familyVo = new UserFamilyVo();
        //1、获取家庭成员实例
        UserInstanceVo instanceVo = userInstanceApi.getUserInstanceById(id).getData();
        BeanUtils.copyProperties(instanceVo, familyVo);
        familyVo.setMemberName(instanceVo.getName());
        //2、获取用户家庭信息
        UserFamilyVo model = userFamilyApi.getUserFamilyByUserId(instanceVo.getId()).getData();
        familyVo.setRelation(model.getRelation());
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
        userInstanceApi.updateUserInstance(userInstanceDTO).getData();
        //2、更新用户家庭
        UserFamilyVo familyVo = userFamilyApi.getUserFamilyByUserId(userFamilyDTO.getId()).getData();
        UserFamilyDTO model = new UserFamilyDTO();
        BeanUtils.copyProperties(userFamilyDTO, model);
        model.setId(familyVo.getId());
        userFamilyApi.updateUserFamilyById(model);
        //3、新增用户扩展信息
        UserExtinfoVo extinfoVo = userExtinfoApi.getUserExtinfoByUserId(userFamilyDTO.getId()).getData();
        UserExtinfoDTO userExtinfoDTO = new UserExtinfoDTO();
        BeanUtils.copyProperties(userFamilyDTO, userExtinfoDTO);
        userExtinfoDTO.setId(extinfoVo.getId());
        userExtinfoApi.updateUserExtinfoById(userExtinfoDTO);
        //4、更新用户工作信息
        UserJobVo jobVo = userJobApi.getUserJobByUserId(userFamilyDTO.getId()).getData();
        UserJobDTO userJobDTO = new UserJobDTO();
        BeanUtils.copyProperties(userFamilyDTO, userJobDTO);
        userJobDTO.setId(jobVo.getId());
        userJobApi.updateUserJobById(userJobDTO);
        //5、更新用户公司信息
        UserCompanyVo companyVo = userCompanyApi.getUserCompanyByUserId(userFamilyDTO.getId()).getData();
        UserCompanyDTO userCompanyDTO = new UserCompanyDTO();
        BeanUtils.copyProperties(userFamilyDTO, userCompanyDTO);
        userCompanyDTO.setId(companyVo.getId());
        userCompanyApi.updateUserCompanyById(userCompanyDTO);
        //6、更新用户教育信息
        UserEducationVo educationVo = userEducationApi.getUserEducationByUserId(userFamilyDTO.getId()).getData();
        UserEducationDTO userEducationDTO = new UserEducationDTO();
        BeanUtils.copyProperties(userFamilyDTO, userEducationDTO);
        userEducationDTO.setId(educationVo.getId());
        userEducationApi.updateUserEducationById(userEducationDTO);
    }

    @ApiOperation("删除 用户-家庭-成员")
    @DeleteMapping("/deleteFamilyMemberById/{id}")
    @Transactional(rollbackFor = Exception.class)
    public void deleteFamilyMemberById(@PathVariable("id") String id) {
        //1、删除家庭成员实例
        userInstanceApi.deleteUserInstanceById(id);
        //2、删除用户家庭
        UserFamilyVo familyVo = userFamilyApi.getUserFamilyByUserId(id).getData();
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
    public void batchDeleteFamilyMembers(@RequestParam("ids") List<String> ids) {
        ids.forEach(id -> {
            //1、删除家庭成员实例
            userInstanceApi.deleteUserInstanceById(id);
            //2、删除用户家庭
            UserFamilyVo familyVo = userFamilyApi.getUserFamilyByUserId(id).getData();
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
        //1、新增家庭户主实例
        UserInstanceDTO userInstanceDTO = new UserInstanceDTO();
        BeanUtils.copyProperties(userFamilyDTO, userInstanceDTO);
        String userInstanceId = userInstanceApi.insertUserInstance(userInstanceDTO).getData();
        //2、新增用户家庭
        userFamilyDTO.setUserId(userInstanceId);
        String familyId = userFamilyApi.insertUserFamily(userFamilyDTO).getData();
        //3、新增用户扩展信息
        UserExtinfoDTO userExtinfoDTO = new UserExtinfoDTO();
        BeanUtils.copyProperties(userFamilyDTO, userExtinfoDTO);
        userExtinfoDTO.setUserId(userInstanceId);
        userExtinfoApi.insertUserExtinfo(userExtinfoDTO);
        //4、插入用户公司信息
        UserCompanyDTO userCompanyDTO = new UserCompanyDTO();
        BeanUtils.copyProperties(userFamilyDTO, userCompanyDTO);
        userCompanyDTO.setUserId(userInstanceId);
        userCompanyApi.insertUserCompany(userCompanyDTO);
        //5、插入用户教育信息
        UserEducationDTO userEducationDTO = new UserEducationDTO();
        BeanUtils.copyProperties(userFamilyDTO, userEducationDTO);
        userEducationDTO.setUserId(userInstanceId);
        userEducationApi.insertUserEducation(userEducationDTO);
        //6、插入用户工作信息
        UserJobDTO userJobDTO = new UserJobDTO();
        BeanUtils.copyProperties(userFamilyDTO, userJobDTO);
        userJobDTO.setUserId(userInstanceId);
        userJobApi.insertUserJob(userJobDTO);
        //7、插入用户住所信息
        UserDwellingDTO userDwellingDTO = new UserDwellingDTO();
        BeanUtils.copyProperties(userFamilyDTO, userDwellingDTO);
        userDwellingDTO.setFamilyId(familyId);
        userDwellingApi.insertUserDwelling(userDwellingDTO);
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
        //2、获取用户实例
        UserInstanceVo instanceVo = userInstanceApi.getUserInstanceById(model.getUserId()).getData();
        BeanUtils.copyProperties(instanceVo, familyVo);
        familyVo.setHouseholderName(instanceVo.getName());
        //3、获取用户扩展信息
        UserExtinfoVo extinfoVo = userExtinfoApi.getUserExtinfoByUserId(model.getUserId()).getData();
        BeanUtils.copyProperties(extinfoVo, familyVo);
        //4、获取用户公司信息
        UserCompanyVo companyVo = userCompanyApi.getUserCompanyByUserId(model.getUserId()).getData();
        BeanUtils.copyProperties(companyVo, familyVo);
        //5、获取用户教育信息
        UserEducationVo educationVo = userEducationApi.getUserEducationByUserId(model.getUserId()).getData();
        BeanUtils.copyProperties(educationVo, familyVo);
        //6、获取用户工作信息
        UserJobVo jobVo = userJobApi.getUserJobByUserId(model.getUserId()).getData();
        BeanUtils.copyProperties(jobVo, familyVo);
        //7、获取用户住所信息
        UserDwellingVo dwellingVo = userDwellingApi.getUserDwellingByFamilyId(id).getData();
        BeanUtils.copyProperties(dwellingVo, familyVo);
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
        userDwellingApi.updateUserDwellingById(userDwellingDTO);
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
        });
    }
}
