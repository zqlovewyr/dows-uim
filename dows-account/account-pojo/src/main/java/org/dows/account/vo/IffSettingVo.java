package org.dows.account.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class IffSettingVo {

	@ApiModelProperty("id")
	private Long id;

	/**
	* 规则标题
	*/
	@ApiModelProperty("规则标题")
	private String title;

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

}

