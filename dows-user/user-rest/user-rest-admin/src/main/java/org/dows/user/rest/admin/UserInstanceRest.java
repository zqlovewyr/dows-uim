package org.dows.user.rest.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.Response;
import org.dows.framework.crud.mybatis.MybatisCrudRest;
import org.dows.user.api.api.UserInstanceApi;
import org.dows.user.api.dto.UserInstanceDTO;
import org.dows.user.api.vo.UserInstanceVo;
import org.dows.user.entity.UserInstance;
import org.dows.user.service.UserInstanceService;
import org.dows.user.form.UserInstanceForm;
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
}

