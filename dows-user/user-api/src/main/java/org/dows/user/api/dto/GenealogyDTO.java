package org.dows.user.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Administrator
 * @date 2023/2/14 13:19
 */
@Data
@ToString
@Builder
@EqualsAndHashCode
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "GenealogyDTO对象", description = "族谱")
public class GenealogyDTO {
    String name;
    String imageUrl;
    String classType;
    List<GenealogyDTO> mate;
    List<GenealogyDTO> children;
}
