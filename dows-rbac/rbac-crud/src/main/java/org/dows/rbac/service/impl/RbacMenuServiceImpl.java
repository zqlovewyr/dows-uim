package org.dows.rbac.service.impl;

import lombok.RequiredArgsConstructor;
import org.dows.framework.crud.mybatis.MybatisCrudServiceImpl;
import org.dows.rbac.entity.RbacMenu;
import org.dows.rbac.mapper.RbacMenuMapper;
import org.dows.rbac.query.RbacMenuQuery;
import org.dows.rbac.service.RbacMenuService;
import org.dows.rbac.vo.RbacMenuVo;
import org.dows.rbac.vo.TreeSelectVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * rbac-菜单(RbacMenu)表服务实现类
 *
 * @author lait.zhang
 * @since 2022-11-24 11:49:45
 */
@Service("rbacMenuService")
@RequiredArgsConstructor
public class RbacMenuServiceImpl extends MybatisCrudServiceImpl<RbacMenuMapper, RbacMenu> implements RbacMenuService {

    private final RbacMenuMapper rbacMenuMapper;

    @Override
    public List<RbacMenuVo> selectMenuListByAccount(RbacMenuQuery menu, String accountId) {
        menu.setAccountId(accountId);
        List<RbacMenuVo>  menuList = rbacMenuMapper.selectMenuListByAccountId(menu);
        return menuList;
    }

    @Override
    public List<RbacMenuVo> buildMenuTree(List<RbacMenuVo> menus) {
        List<RbacMenuVo> returnList = new ArrayList<RbacMenuVo>();
        List<Long> tempList = menus.stream().map(RbacMenuVo::getId).collect(Collectors.toList());
        for (Iterator<RbacMenuVo> iterator = menus.iterator(); iterator.hasNext();)
        {
            RbacMenuVo menu = (RbacMenuVo) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(menu.getPid()))
            {
                recursionFn(menus, menu);
                returnList.add(menu);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = menus;
        }
        return returnList;
    }

    @Override
    public List<TreeSelectVo> buildMenuTreeSelect(List<RbacMenuVo> menus) {
        List<RbacMenuVo> menuTrees = buildMenuTree(menus);
        return menuTrees.stream().map(TreeSelectVo::new).collect(Collectors.toList());
    }

    @Override
    public List<RbacMenuVo> selectMenuList(RbacMenuQuery menu) {
        return rbacMenuMapper.selectMenuList(menu);
    }

    /**
     * 递归列表
     *
     * @param list 分类表
     * @param t 子节点
     */
    private void recursionFn(List<RbacMenuVo> list, RbacMenuVo t)
    {
        // 得到子节点列表
        List<RbacMenuVo> childList = getChildList(list, t);
        t.setChildren(childList);
        for (RbacMenuVo tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }
    /**
     * 得到子节点列表
     */
    private List<RbacMenuVo> getChildList(List<RbacMenuVo> list, RbacMenuVo t)
    {
        List<RbacMenuVo> tlist = new ArrayList<RbacMenuVo>();
        Iterator<RbacMenuVo> it = list.iterator();
        while (it.hasNext())
        {
            RbacMenuVo n = (RbacMenuVo) it.next();
            if (n.getPid().longValue() == t.getId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }
    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<RbacMenuVo> list, RbacMenuVo t)
    {
        return getChildList(list, t).size() > 0;
    }
}

