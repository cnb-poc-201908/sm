package com.bmw.sm.rspmodel;

import javax.validation.constraints.NotNull;

import com.bmw.sm.model.AppHealthModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "数据健康状态和指标返回模型")
public class RspStatusModel {
	@ApiModelProperty(notes = "返回码", example = "200", required = true, dataType = "java.lang.Integer")
	@NotNull
	private Integer code;

	@ApiModelProperty(notes = "返回信息", example = "成功", required = true, dataType = "java.lang.String")
	@NotNull
	private String message;

	@ApiModelProperty(notes = "返回数据", required = true, dataType = "java.lang.Object")
	private AppHealthModel data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AppHealthModel getData() {
		return data;
	}

	public void setData(AppHealthModel data) {
		this.data = data;
	}

	public RspStatusModel(@NotNull Integer code, @NotNull String message, AppHealthModel data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public RspStatusModel() {
		super();
		// TODO Auto-generated constructor stub
	}

}
