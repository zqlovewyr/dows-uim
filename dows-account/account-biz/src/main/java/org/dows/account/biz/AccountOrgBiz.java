package org.dows.account.biz;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.account.biz.dto.TreeAccountOrgDTO;
import org.dows.account.biz.util.AccountUtil;
import org.dows.account.entity.AccountOrg;
import org.dows.account.service.AccountOrgService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Slf4j
@Service
public class AccountOrgBiz {

    private final AccountOrgService accountOrgService;

    // todo 导入文件
    public void importOrg(String file){

    }

    // todo 导入组织对象
    public void importOrg(TreeAccountOrgDTO file){


    }

    // todo 列出所有组织记录
    public void listOrg(){

    }
    // todo 根据条件列出所有层级组织(智能层级，所有组织)
    public void listOrgTree(TreeAccountOrgDTO treeAccountOrgDTO){




    }

    /**
     * 创建树形结构 accountOrg 账号-组织
     *
     * @param treeAccountOrgDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void createTreeAccountOrg(TreeAccountOrgDTO treeAccountOrgDto) {
        // step1:check static rule
        AccountUtil.validateAccountOrgDTO(treeAccountOrgDto);
        // step2:insert parentAccountOrg
        AccountOrg parentAccountOrg = new AccountOrg();
        BeanUtils.copyProperties(treeAccountOrgDto, parentAccountOrg);
        parentAccountOrg.setOrgId(IdWorker.getIdStr());
        parentAccountOrg.setPid(0L);
        accountOrgService.save(parentAccountOrg);
        // step3:recursion insert sonAccountOrg
        List<TreeAccountOrgDTO> sonAccountOrgList = treeAccountOrgDto.getNextAccountOrgDto();
        List<AccountOrg> accountOrgs = this.toArrayTree(sonAccountOrgList, parentAccountOrg.getId());
        accountOrgs.add(parentAccountOrg);
    }

    /**
     * @param array tree array
     * @param pId   current parent Id
     */
    private List<AccountOrg> toArrayTree(List<TreeAccountOrgDTO> array, long pId) {
        if (CollectionUtils.isEmpty(array)) {
            return new ArrayList<>();
        }
        List<AccountOrg> treeOrg = new ArrayList<>();
        for (TreeAccountOrgDTO orgDTO : array) {
            //check static rule
            AccountUtil.validateAccountOrgDTO(orgDTO);
            //insert childAccountOrg
            AccountOrg accountOrg = new AccountOrg();
            BeanUtils.copyProperties(orgDTO, accountOrg);
            accountOrg.setOrgId(IdWorker.getIdStr());
            accountOrg.setPid(pId);
            accountOrgService.save(accountOrg);
            treeOrg.add(accountOrg);
            //recursion
            List<TreeAccountOrgDTO> nextAccountOrgDto = orgDTO.getNextAccountOrgDto();
            treeOrg.addAll(this.toArrayTree(nextAccountOrgDto, accountOrg.getId()));
        }
        return treeOrg;
    }

}
