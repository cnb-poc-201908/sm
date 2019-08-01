package com.bmw.sm.model;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "应用健康状态指标模型")
public class AppHealthModel {
	@ApiModelProperty(notes = "应用指标", required = true, dataType = "java.lang.Object")
	@NotNull
	private MetricsModel metrics;
	@ApiModelProperty(notes = "应用状态", required = true, dataType = "java.lang.List")
	@NotNull
	private ArrayList<HealthModel> status;

	public MetricsModel getMetrics() {
		return metrics;
	}

	public void setMetrics(MetricsModel metrics) {
		this.metrics = metrics;
	}

	public ArrayList<HealthModel> getStatus() {
		return status;
	}

	public void setStatus(ArrayList<HealthModel> status) {
		this.status = status;
	}

	public AppHealthModel(@NotNull MetricsModel metrics, @NotNull ArrayList<HealthModel> status) {
		super();
		this.metrics = metrics;
		this.status = status;
	}

	public AppHealthModel() {
		super();
		// TODO Auto-generated constructor stub
	}

}
