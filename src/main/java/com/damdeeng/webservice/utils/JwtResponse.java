package com.damdeeng.webservice.utils;

import io.jsonwebtoken.Jwt;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@Getter
@Setter
public class JwtResponse {

	public int status = 0;
	public String message = "";
	public String timestamp;
	public Object response;

	public static JwtResponse newInstance() {

		JwtResponse response = new JwtResponse();

		response.status = ResponseCode.SUCCESS.status;
		response.message = ResponseCode.SUCCESS.message;
		response.timestamp = new Date().toInstant().atZone(ZoneId.systemDefault()).toString();
		response.response  = new HashMap<String, Object>();

		return response;
	}

	public static JwtResponse newInstance(ResponseCode code) {

		JwtResponse response = new JwtResponse();

		if(code != null) {
			response.status = code.status;
			response.message = code.message;
		} else {
			response.status = ResponseCode.SUCCESS.status;
			response.message = ResponseCode.SUCCESS.message;
		}

		response.timestamp = new Date().toInstant().atZone(ZoneId.systemDefault()).toString();
		response.response  = new HashMap<String, Object>();

		return response;
	}

	public JwtResponse setResponseCode(ResponseCode code) {
		this.status  = code.status;
		this.message = code.message;
		return this;
	}

	public JwtResponse appendStatus(String appendString) {
		this.message += appendString;
		return this;
	}

	public ResponseEntity<JwtResponse> build() {
		this.timestamp = new Date().toInstant().atZone(ZoneId.systemDefault()).toString();
		return new ResponseEntity<>(this, HttpStatus.OK);
	}

	public ResponseEntity<JwtResponse> build(ResponseCode code) {
		if (code != null) {
			this.status  = code.status;
			this.message = code.message;
		}
		this.timestamp = new Date().toInstant().atZone(ZoneId.systemDefault()).toString();

		return new ResponseEntity<>(this, HttpStatus.OK);
	}

	public ResponseEntity<JwtResponse> build(ResponseCode code, Exception e) {
		if (code != null) {
			this.status  = code.status;
			this.message = e.getMessage();
		}
		this.timestamp = new Date().toInstant().atZone(ZoneId.systemDefault()).toString();

		return new ResponseEntity<>(this, HttpStatus.OK);
	}

	public ResponseEntity<JwtResponse> build(HttpStatus status) {

		this.timestamp = new Date().toInstant().atZone(ZoneId.systemDefault()).toString();

		return new ResponseEntity<>(this, status);
	}

	@SuppressWarnings("unchecked")
	public JwtResponse addData(String key, Object value) {
		if (!ObjectUtils.isEmpty(response) && !(response instanceof HashMap)) return this;

		if (ObjectUtils.isEmpty(response)) response = new HashMap<String, Object>();

		HashMap<String, Object> data = (HashMap<String, Object>)response;
		if (key.equals("response")) {
			response = value;
		} else {
			if (data.containsKey(key)) data.remove(key);
			data.put(key, value);
		}
		return this;
	}






}
