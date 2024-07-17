package com.shofydrop.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseDto {
    private HttpStatus status;
    private String message;
    private Object data;
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
