package org.dows.rbac.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermQuery {
    @NotNull(message = "角色Id不可为空")
    private Long roleId;
    private Long categoryId;
}
