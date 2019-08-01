package com.bmw.sm.rspmodel;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import com.bmw.sm.model.ApplicationModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "数据返回模型")
public class RspModel {

	@ApiModelProperty(notes = "返回码", example = "200", required = true, dataType = "java.lang.Integer")
	@NotNull
	private Integer code;

	@ApiModelProperty(notes = "返回信息", example = "成功", required = true, dataType = "java.lang.String")
	@NotNull
	private String message;

	@ApiModelProperty(notes = "返回数据", required = true, dataType = "java.lang.List")
	private ArrayList<ApplicationModel> data;

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

	public ArrayList<ApplicationModel> getData() {
		return data;
	}

	public void setData(ArrayList<ApplicationModel> data) {
		this.data = data;
	}

	public RspModel(@NotNull Integer code, @NotNull String message, ArrayList<ApplicationModel> data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public RspModel() {
		super();
		// TODO Auto-generated constructor stub
	}

}
