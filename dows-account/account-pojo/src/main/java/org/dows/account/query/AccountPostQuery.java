package org.dows.account.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 账号-职位(AccountPost)Query类
 * @author: VX:PN15855012581
 * @create: 2021-08-25 14:24:46
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AccountPost对象", description = "账号-职位")
public class AccountPostQuery implements Serializable {
    private static final long serialVersionUID = 187775626331310281L;

    @ApiModelProperty(value = "分页查询起始位置")
    private Integer offset = 0;
    @ApiModelProperty(value = "每页查询记录数")
    private Integer size = 10;

    @ApiModelProperty(value = "账号-职业ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @ApiModelProperty(value = "职位编号")
    private String postCode;

    @ApiModelProperty(value = "职位名称")
    private String postName;

    @ApiModelProperty(value = "职位描述")
    private String post_des;



}

