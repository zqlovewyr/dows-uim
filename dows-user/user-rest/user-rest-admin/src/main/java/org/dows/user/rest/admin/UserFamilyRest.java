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
import org.dows.user.api.vo.UserFamilyVo;
import org.dows.user.entity.UserFamily;
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

    @ApiOperation("查询 用户-家庭-户主-列表")
    @PostMapping("/getFamilyArchivesList")
    public Response<IPage<UserFamilyVo>> getFamilyArchivesList(@RequestBody UserFamilyDTO userFamilyDTO) {
        Response<IPage<UserFamilyVo>> pageList = userFamilyApi.getFamilyArchivesList(userFamilyDTO);
        return pageList;
    }

    @ApiOperation("新增 用户-家庭")
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

    @ApiOperation("编辑 用户-家庭")
    @PostMapping("/updateUserFamilyById")
    public Response<Long> updateUserFamilyById(@RequestBody UserFamilyDTO userFamilyDTO) {
        Response<Long> id = userFamilyApi.updateUserFamilyById(userFamilyDTO);
        return id;
    }

    @ApiOperation("批量删除 用户-家庭")
    @PostMapping("/batchDeleteUserFamilys")
    public void batchDeleteUserFamilys(@RequestParam("ids") List<String> ids) {
        userFamilyApi.batchDeleteUserFamilys(ids);
    }
}
