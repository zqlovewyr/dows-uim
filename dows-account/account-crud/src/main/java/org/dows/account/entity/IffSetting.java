package org.dows.account.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "规则设置", description = "规则设置")
public class IffSetting{

	/**
	* 自增id
	*/
	@ApiModelProperty("uuid")
	private String id;

	/**
	* 创建时间
	*/ 
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date createTime;

	/**
	* 编辑时间
	*/ 
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date updateTime;

	/**
	* 规则编号
	*/
	@ApiModelProperty("规则编号")
	private Integer ruleNum;

	/**
	* 规则标题
	*/
	@ApiModelProperty("规则标题")
	private String title;

	/**
	* 规则描述
	*/
	@ApiModelProperty("规则描述")
	private String remark;

	/**
	 * 规则内容体
	 */
	@ApiModelProperty("规则内容体")
	private String content;

	/**
	 * 门店ID
	 */
	@ApiModelProperty("门店ID")
	private String storeId;

	@ApiModelProperty("参数类型  1系统参数 2村参数  默认1")
	private Integer settingType;

}

