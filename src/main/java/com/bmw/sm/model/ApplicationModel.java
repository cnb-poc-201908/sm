package com.bmw.sm.model;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "微服务应用模型")
public class ApplicationModel {
	@ApiModelProperty(notes = "服务名字", example = "bmw-服务名字", required = true, dataType = "java.lang.String")
	@NotNull
	private String name;
	@ApiModelProperty(notes = "服务实例列表", example = "服务实例信息", required = true, dataType = "java.lang.List")
	@NotNull
	private ArrayList<InstanceModel> instance;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<InstanceModel> getInstance() {
		return instance;
	}

	public void setInstance(ArrayList<InstanceModel> instance) {
		this.instance = instance;
	}

	public ApplicationModel(@NotNull String name, @NotNull ArrayList<InstanceModel> instance) {
		super();
		this.name = name;
		this.instance = instance;
	}

	public ApplicationModel() {
		super();
		// TODO Auto-generated constructor stub
	}

}
