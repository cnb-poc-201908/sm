package com.bmw.sm.model;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "微服务应用健康模型")
public class HealthModel {

	@ApiModelProperty(notes = "应用组件名字", example = "db", required = true, dataType = "java.lang.String")
	@NotNull
	private String name;
	@ApiModelProperty(notes = "应用状态", example = "UP, DOWN 和 UNKNOW", required = true, dataType = "java.lang.String")
	@NotNull
	private String status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public HealthModel(@NotNull String name, @NotNull String status) {
		super();
		this.name = name;
		this.status = status;
	}

	public HealthModel() {
		super();
		// TODO Auto-generated constructor stub
	}

}
