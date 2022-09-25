package org.dows.rbac.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class PermSimpleVo {
    //主键ID
    //@JsonSerialize(using= ToStringSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    private String permissionName;//permission_name
    private String permissionCode;
    private Long resourcePid;
    private Integer level;
    private List<PermSimpleVo> children;
}
