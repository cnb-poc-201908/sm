package com.bmw.sm.model;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "微服务状态更新模型")
public class UpdateStatusModel {
	@ApiModelProperty(notes = "服务应用ID", example = "服务应用名", required = true, dataType = "java.lang.String")
	@NotNull
	private String appId;
	@ApiModelProperty(notes = "服务实例ID", example = "服务应用名:端口号", required = true, dataType = "java.lang.String")
	@NotNull
	private String instId;
	@ApiModelProperty(notes = "服务状态", example = "UP 或者 DOWN", required = true, dataType = "java.lang.String")
	@NotNull
	private Status value;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getInstId() {
		return instId;
	}
	public void setInstId(String instId) {
		this.instId = instId;
	}
	public Status getValue() {
		return value;
	}
	public void setValue(Status value) {
		this.value = value;
	}
	public UpdateStatusModel(String appId, String instId, Status value) {
		super();
		this.appId = appId;
		this.instId = instId;
		this.value = value;
	}
	public UpdateStatusModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}