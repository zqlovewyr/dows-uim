package org.dows.user.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.framework.api.Response;
import org.dows.user.api.api.UserFamilyApi;
import org.dows.user.api.dto.UserFamilyDTO;
import org.dows.user.api.vo.UserFamilyVo;
import org.dows.user.entity.UserFamily;
import org.dows.user.enums.EnumUserStatusCode;
import org.dows.user.exception.UserException;
import org.dows.user.service.UserFamilyService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
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
    public Response<Long> insertUserFamily(UserFamilyDTO userFamilyDTO) {
        UserFamily model = new UserFamily();
        BeanUtils.copyProperties(userFamilyDTO,model);
        boolean flag = userFamilyService.save(model);
        if(flag == false){
            throw new UserException(EnumUserStatusCode.USER_FAMILY_CREATE_FAIL_EXCEPTION);
        }
        return Response.ok(model.getId());
    }

}
