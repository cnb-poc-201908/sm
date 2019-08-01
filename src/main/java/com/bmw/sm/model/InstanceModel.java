package com.bmw.sm.model;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "微服务实例模型")
public class InstanceModel {
	@ApiModelProperty(notes = "服务实例ID", example = "服务应用名:端口号", required = true, dataType = "java.lang.String")
	@NotNull
	private String instanceId;
	@ApiModelProperty(notes = "服务主机名", example = "bmw-example", required = true, dataType = "java.lang.String")
	@NotNull
	private String hostName;
	@ApiModelProperty(notes = "服务应用名", example = "bmw-example", required = true, dataType = "java.lang.String")
	@NotNull
	private String app;
	@ApiModelProperty(notes = "服务IP地址", example = "xx.xx.xx.xx", required = true, dataType = "java.lang.String")
	@NotNull
	private String ipAddr;
	@ApiModelProperty(notes = "服务状态", example = "UP 或者 DOWN", required = true, dataType = "java.lang.String")
	@NotNull
	private String status;
	@ApiModelProperty(notes = "服务端口号", example = "8000", required = true, dataType = "java.lang.Integer")
	@NotNull
	private Integer port;
	@ApiModelProperty(notes = "服务健康URL", example = "http://IP:PORT/actuator/health", required = true, dataType = "java.lang.String")
	@NotNull
	private String healthCheckUrl;
	@ApiModelProperty(notes = "服务实例地址L", example = "xx.xx.xx.xx", required = true, dataType = "java.lang.String")
	@NotNull
	private String vipAddress;
	@ApiModelProperty(notes = "服务更新时间", example = "unix时间制", required = true, dataType = "java.lang.String")
	@NotNull
	private String lastUpdatedTimestamp;
	@ApiModelProperty(notes = "服务失败时间", example = "unix时间制", required = true, dataType = "java.lang.String")
	@NotNull
	private String lastDirtyTimestamp;

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getHealthCheckUrl() {
		return healthCheckUrl;
	}

	public void setHealthCheckUrl(String healthCheckUrl) {
		this.healthCheckUrl = healthCheckUrl;
	}

	public String getVipAddress() {
		return vipAddress;
	}

	public void setVipAddress(String vipAddress) {
		this.vipAddress = vipAddress;
	}

	public String getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}

	public void setLastUpdatedTimestamp(String lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}

	public String getLastDirtyTimestamp() {
		return lastDirtyTimestamp;
	}

	public void setLastDirtyTimestamp(String lastDirtyTimestamp) {
		this.lastDirtyTimestamp = lastDirtyTimestamp;
	}

	public InstanceModel(String instanceId, String hostName, String app, String ipAddr, String status, Integer port,
			String healthCheckUrl, String vipAddress, String lastUpdatedTimestamp, String lastDirtyTimestamp) {
		super();
		this.instanceId = instanceId;
		this.hostName = hostName;
		this.app = app;
		this.ipAddr = ipAddr;
		this.status = status;
		this.port = port;
		this.healthCheckUrl = healthCheckUrl;
		this.vipAddress = vipAddress;
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
		this.lastDirtyTimestamp = lastDirtyTimestamp;
	}

	public InstanceModel() {
		super();
		// TODO Auto-generated constructor stub
	}

}
