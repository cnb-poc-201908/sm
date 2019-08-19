package com.bmw.sm.apicontroller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.bmw.sm.config.RegistryUrl;
import com.bmw.sm.model.ApplicationModel;
import com.bmw.sm.model.InstanceModel;
import com.bmw.sm.model.UpdateStatusModel;
import com.bmw.sm.rspmodel.RspModel;
import com.bmw.sm.rspmodel.StatusModel;
import com.bmw.sm.service.ConsumeRegistry;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
@Api(description = "BMW微服务平台服务接口")
public class ServiceManagementApi {
	@Autowired
	private RegistryUrl r_rul;

	@Autowired
	private ConsumeRegistry consumeregistry;

	@GetMapping(value = "/apps", produces = "application/json")
	@ApiOperation(value = "微服务信息查询")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 400, message = "错误的请求"),
			@ApiResponse(code = 401, message = "没有权限查看此资源"), @ApiResponse(code = 404, message = "资源不存在"),
			@ApiResponse(code = 500, message = "内部错误请联系管理员") })
	public ResponseEntity<RspModel> getApps() {
		// 消费Eureka 注册Rest 接口
		try {
			ResponseEntity<Object> result = consumeregistry.ConsumeRegistryApps(r_rul.getUrl());

			Gson gson = new Gson();
			String jsonResultStr = gson.toJson(result.getBody());
			JsonParser parser = new JsonParser();
			JsonObject object = (JsonObject) parser.parse(jsonResultStr);
			// JsonElement applications = object.get("application").getAsJsonObject();

			JsonArray listdata = object.get("applications").getAsJsonObject().get("application").getAsJsonArray();
			if (listdata != null) {
				ArrayList<ApplicationModel> apps = new ArrayList<>();
				for (int i = 0; i < listdata.size(); i++) {
					ApplicationModel app = new ApplicationModel();
					ArrayList<InstanceModel> instance = new ArrayList<>();
					app.setName(listdata.get(i).getAsJsonObject().get("name").getAsString());
					JsonArray instdata = listdata.get(i).getAsJsonObject().get("instance").getAsJsonArray();
					for (int k = 0; k < instdata.size(); k++) {
						InstanceModel inst = new InstanceModel();
						inst.setInstanceId(instdata.get(k).getAsJsonObject().get("instanceId").getAsString());
						inst.setHostName(instdata.get(k).getAsJsonObject().get("hostName").getAsString());
						inst.setApp(instdata.get(k).getAsJsonObject().get("app").getAsString());
						inst.setIpAddr(instdata.get(k).getAsJsonObject().get("ipAddr").getAsString());
						inst.setStatus(instdata.get(k).getAsJsonObject().get("status").getAsString());
						inst.setPort(
								instdata.get(k).getAsJsonObject().get("port").getAsJsonObject().get("$").getAsInt());
						inst.setHealthCheckUrl(instdata.get(k).getAsJsonObject().get("healthCheckUrl").getAsString());
						inst.setVipAddress(instdata.get(k).getAsJsonObject().get("vipAddress").getAsString());
						inst.setLastUpdatedTimestamp(
								instdata.get(k).getAsJsonObject().get("lastUpdatedTimestamp").getAsString());
						inst.setLastDirtyTimestamp(
								instdata.get(k).getAsJsonObject().get("lastDirtyTimestamp").getAsString());
						instance.add(inst);
					}
					app.setInstance(instance);
					apps.add(app);

				}

				RspModel rspresult = new RspModel();
				rspresult.setCode(200);
				rspresult.setData(apps);
				rspresult.setMessage("成功");

				return new ResponseEntity<RspModel>(rspresult, HttpStatus.OK);

			} else {
				RspModel rspresult = new RspModel();
				rspresult.setCode(404);
				rspresult.setMessage("没有可用服务");

				return new ResponseEntity<RspModel>(rspresult, HttpStatus.NOT_FOUND);

			}

		} catch (RestClientException e) {
			RspModel rspresult = new RspModel();
			rspresult.setCode(500);
			rspresult.setMessage("服务不可用" + e.getMessage());

			return new ResponseEntity<RspModel>(rspresult, HttpStatus.INTERNAL_SERVER_ERROR);

			// throw e;
		}

	}


	@PostMapping(value = "/updatestatus", produces = "application/json")
	@ApiOperation(value = "服务状态更新")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "更新成功"), @ApiResponse(code = 400, message = "错误的请求"),
			@ApiResponse(code = 401, message = "没有权限查看此资源"), @ApiResponse(code = 404, message = "资源不存在"),
			@ApiResponse(code = 500, message = "内部错误请联系管理员") })
	public ResponseEntity<StatusModel> updateStatus(
			@ApiParam(name = "body", value = "All of Parameters required", required = true) @RequestBody UpdateStatusModel updatestatus) {

		// List<String> status = new ArrayList<String>();
		// status.add("UP");
		// status.add("DOWN");
		// status.add("OUT_OF_SERVICE");
		// if (!status.contains(updatestatus.getValue())) {
		// StatusModel statusresult = new StatusModel();
		// statusresult.setCode(400);
		// statusresult.setMessage("请检查状态参数");
		// return new ResponseEntity<StatusModel>(statusresult, HttpStatus.BAD_REQUEST);
		// }
		// 消费Eureka 注册Rest 接口
		try {
			ResponseEntity<Object> result = consumeregistry.ConsumeUpdateStatus(r_rul.getUrl(), updatestatus.getAppId(),
					updatestatus.getInstId(), updatestatus.getValue());

			if (result.getStatusCodeValue() == 200) {
				StatusModel statusresult = new StatusModel();
				statusresult.setCode(200);
				statusresult.setMessage("更新成功");

				return new ResponseEntity<StatusModel>(statusresult, HttpStatus.OK);

			} else {
				StatusModel statusresult = new StatusModel();
				statusresult.setCode(500);
				statusresult.setMessage("更新失败");

				return new ResponseEntity<StatusModel>(statusresult, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (RestClientException e) {
			StatusModel statusresult = new StatusModel();
			statusresult.setCode(500);
			statusresult.setMessage("服务不可用" + e.getMessage());

			return new ResponseEntity<StatusModel>(statusresult, HttpStatus.INTERNAL_SERVER_ERROR);

			// throw e;
		}

	}

}
