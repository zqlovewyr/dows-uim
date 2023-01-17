package org.dows.rbac.service;

import org.dows.rbac.entity.RbacMenu;
import org.dows.framework.crud.mybatis.MybatisCrudService;
import org.dows.rbac.query.RbacMenuQuery;
import org.dows.rbac.vo.RbacMenuVo;
import org.dows.rbac.vo.TreeSelectVo;

import java.util.List;


/**
 * rbac-菜单(RbacMenu)表服务接口
 *
 * @author lait.zhang
 * @since 2022-11-24 11:49:45
 */
public interface RbacMenuService extends MybatisCrudService<RbacMenu> {

    /**
     * 查询所有
     * @param menu
     * @return
     */
    public List<RbacMenuVo> selectMenuList(RbacMenuQuery menu);

    /**
     * 根据用户查询系统菜单列表
     *
     * @param menu 菜单信息
     * @param accountId 用户ID
     * @return 菜单列表
     */
    public List<RbacMenuVo> selectMenuListByAccount(RbacMenuQuery menu, String accountId);


    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */
    public List<RbacMenuVo> buildMenuTree(List<RbacMenuVo> menus);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param menus 菜单列表
     * @return 下拉树结构列表
     */
    public List<TreeSelectVo> buildMenuTreeSelect(List<RbacMenuVo> menus);

}

