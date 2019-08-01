package com.bmw.sm.apicontroller;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.bmw.sm.model.AppHealthModel;
import com.bmw.sm.model.HealthModel;
import com.bmw.sm.model.MetricsModel;
import com.bmw.sm.rspmodel.RspStatusModel;
import com.bmw.sm.service.ConsumeAppImpl;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
@Api(description = "BMW微服务平台应用接口")
public class AppManagementApi {

	@Autowired
	private ConsumeAppImpl consumeapp;

	@RequestMapping(value = "/health", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "微服务应用状态查询")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 400, message = "错误的请求"),
			@ApiResponse(code = 401, message = "没有权限查看此资源"), @ApiResponse(code = 404, message = "资源不存在"),
			@ApiResponse(code = 500, message = "内部错误请联系管理员") })
	public ResponseEntity<RspStatusModel> getAppHealth(
			@ApiParam(name = "appName", value = "bmw-xxx", required = true) @RequestParam("appName") String appName) {
		// 消费App Health endpoint Rest 接口
		try {

			ResponseEntity<Object> result = consumeapp.ConsumeAppHealth(appName);
			// Gson gson = new Gson();
			// String jsonResultStr = gson.toJson(result.getBody());
			// JsonParser parser = new JsonParser();
			// JsonObject object = (JsonObject) parser.parse(jsonResultStr);
			JsonObject object = getResult(result);
			JsonObject details = object.getAsJsonObject("details");
			ArrayList<HealthModel> healths = new ArrayList<>();
			for (Iterator iterator = details.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();

				HealthModel health = new HealthModel();
				health.setName(key);

				JsonElement status = details.get(key).getAsJsonObject().get("status");
				health.setStatus(status.getAsString().replace("\"", ""));
				healths.add(health);
			}

			MetricsModel metrics_response = new MetricsModel();
			// String[] metrics = {"jvm.memory.max", "jvm.memory.used", "process.uptime",
			// "system.cpu.usage", "http.server.requests", "process.start.time"};
			String jvm_memory_max = "jvm.memory.max";
			ResponseEntity<Object> jvm_memory_max_result = consumeapp.ConsumeAppMetric(appName, jvm_memory_max);
			JsonObject jvm_memory_max_object = getResult(jvm_memory_max_result);
			JsonArray jvm_memory_max_measurements = jvm_memory_max_object.get("measurements").getAsJsonArray();
			metrics_response.setJvm_max_memory(
					jvm_memory_max_measurements.get(0).getAsJsonObject().get("value").getAsLong());

			String jvm_memory_used = "jvm.memory.used";
			ResponseEntity<Object> jvm_memory_used_result = consumeapp.ConsumeAppMetric(appName, jvm_memory_used);
			JsonObject jvm_memory_used_object = getResult(jvm_memory_used_result);
			JsonArray jvm_memory_used_measurements = jvm_memory_used_object.get("measurements").getAsJsonArray();
			metrics_response.setJvm_used_memory(
					jvm_memory_used_measurements.get(0).getAsJsonObject().get("value").getAsLong());

			String process_uptime = "process.uptime";
			ResponseEntity<Object> process_uptime_result = consumeapp.ConsumeAppMetric(appName, process_uptime);
			JsonObject process_uptime_object = getResult(process_uptime_result);
			JsonArray process_uptime_measurements = process_uptime_object.get("measurements").getAsJsonArray();
			metrics_response.setProcess_uptime(
					process_uptime_measurements.get(0).getAsJsonObject().get("value").getAsDouble());

			String system_cpu_usage = "system.cpu.usage";
			ResponseEntity<Object> system_cpu_usage_result = consumeapp.ConsumeAppMetric(appName, system_cpu_usage);
			JsonObject system_cpu_usage_object = getResult(system_cpu_usage_result);
			JsonArray system_cpu_usage_measurements = system_cpu_usage_object.get("measurements").getAsJsonArray();
			metrics_response.setSystem_cpu_usage(
					system_cpu_usage_measurements.get(0).getAsJsonObject().get("value").getAsDouble());

			String http_server_requests = "http.server.requests";
			ResponseEntity<Object> http_server_requests_result = consumeapp.ConsumeAppMetric(appName,
					http_server_requests);
			JsonObject http_server_requests_object = getResult(http_server_requests_result);
			JsonArray http_server_requestse_measurements = http_server_requests_object.get("measurements").getAsJsonArray();
			metrics_response.setHttp_server_requests(
					http_server_requestse_measurements.get(0).getAsJsonObject().get("value").getAsLong());

			String process_start_time = "process.start.time";
			ResponseEntity<Object> process_start_time_result = consumeapp.ConsumeAppMetric(appName, process_start_time);
			JsonObject process_start_time_object = getResult(process_start_time_result);
			JsonArray process_start_time_measurements = process_start_time_object.get("measurements").getAsJsonArray();
			metrics_response.setProcess_start_time(
					process_start_time_measurements.get(0).getAsJsonObject().get("value").getAsLong());

			AppHealthModel apphealth = new AppHealthModel();
			apphealth.setStatus(healths);
			apphealth.setMetrics(metrics_response);
			RspStatusModel rspresult = new RspStatusModel();
			rspresult.setCode(200);
			rspresult.setData(apphealth);
			rspresult.setMessage("成功");

			return new ResponseEntity<RspStatusModel>(rspresult, HttpStatus.OK);

		} catch (RestClientException e) {
			RspStatusModel rspresult = new RspStatusModel();
			rspresult.setCode(500);
			rspresult.setMessage("服务不可用" + e.getMessage());

			return new ResponseEntity<RspStatusModel>(rspresult, HttpStatus.INTERNAL_SERVER_ERROR);

			// throw e;
		}

	}

	private JsonObject getResult(ResponseEntity<Object> result) {
		Gson gson = new Gson();
		String jsonResultStr = gson.toJson(result.getBody());
		JsonParser parser = new JsonParser();
		JsonObject object = (JsonObject) parser.parse(jsonResultStr);

		return object;

	}

}