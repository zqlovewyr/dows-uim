package org.dows.account.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 账号-主体角色维度信息(PrincipalRole)DTO类
 * @author: VX:PN15855012581
 * @create: 2021-09-17 14:02:52
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrincipalRoleDto implements Serializable {
    private static final long serialVersionUID = -31122125836499478L;

    //id
    private Long id;
    //角色ID
    private Long roleId;
    //角色名
    private String roleName;
    //角色码
    private String roleCode;
    //主体ID(账号/角色)
    private Long principalId;
    //主体名称
    private String principalName;
    //主体类型(0：账号，1：组)
    private Integer principalTyp;
    //租户ID
    private String tenantId;
    //乐观锁, 默认: 0
    private Integer ver;
    //是否逻辑删除: 0 未删除(false), 1 已删除(true); 默认: 0
    private Boolean deleted;
    //时间戳/创建时间
    private Date dt;


}
