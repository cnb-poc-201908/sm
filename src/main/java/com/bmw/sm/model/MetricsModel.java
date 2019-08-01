package com.bmw.sm.model;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "微服务应用指标")
public class MetricsModel {
	@ApiModelProperty(notes = "应用最大内存 bytes", example = "3454009343", required = true, dataType = "java.lang.Long")
	@NotNull
	private Long jvm_max_memory; // 应用最大JVM内存 bytes
	@ApiModelProperty(notes = "应用使用内存 bytes", example = "253736480", required = true, dataType = "java.lang.Long")
	@NotNull
	private Long jvm_used_memory; // 应用使用JVM内存 bytes
	@ApiModelProperty(notes = "应用运行时间 秒", example = "1494.704", required = true, dataType = "java.lang.Double")
	@NotNull
	private Double process_uptime; // 应用启动时间 秒
	@ApiModelProperty(notes = "应用CPU使用率", example = "0.5874070736652399", required = true, dataType = "java.lang.Double")
	@NotNull
	private Double system_cpu_usage; // 应用CPU使用
	@ApiModelProperty(notes = "应用请求数", example = "100", required = true, dataType = "java.lang.Long")
	@NotNull
	private Long http_server_requests; // 应用请求数
	@ApiModelProperty(notes = "应用启动时间 Unix时间制", example = "1556201009.91", required = true, dataType = "java.lang.Long")
	@NotNull
	private Long process_start_time; // 应用开始时间 unix timestamp

	public Long getJvm_max_memory() {
		return jvm_max_memory;
	}

	public void setJvm_max_memory(Long jvm_max_memory) {
		this.jvm_max_memory = jvm_max_memory;
	}

	public Long getJvm_used_memory() {
		return jvm_used_memory;
	}

	public void setJvm_used_memory(Long jvm_used_memory) {
		this.jvm_used_memory = jvm_used_memory;
	}

	public Double getProcess_uptime() {
		return process_uptime;
	}

	public void setProcess_uptime(Double process_uptime) {
		this.process_uptime = process_uptime;
	}

	public Double getSystem_cpu_usage() {
		return system_cpu_usage;
	}

	public void setSystem_cpu_usage(Double system_cpu_usage) {
		this.system_cpu_usage = system_cpu_usage;
	}

	public Long getHttp_server_requests() {
		return http_server_requests;
	}

	public void setHttp_server_requests(Long http_server_requests) {
		this.http_server_requests = http_server_requests;
	}

	public Long getProcess_start_time() {
		return process_start_time;
	}

	public void setProcess_start_time(Long process_start_time) {
		this.process_start_time = process_start_time;
	}

	public MetricsModel(@NotNull Long jvm_max_memory, @NotNull Long jvm_used_memory, @NotNull Double process_uptime,
			@NotNull Double system_cpu_usage, @NotNull Long http_server_requests, @NotNull Long process_start_time) {
		super();
		this.jvm_max_memory = jvm_max_memory;
		this.jvm_used_memory = jvm_used_memory;
		this.process_uptime = process_uptime;
		this.system_cpu_usage = system_cpu_usage;
		this.http_server_requests = http_server_requests;
		this.process_start_time = process_start_time;
	}

	public MetricsModel() {
		super();
		// TODO Auto-generated constructor stub
	}

}