package com.shofydrop.dto;

import com.shofydrop.entity.Users;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseDto {
    private HttpStatus status;
    private String message;
    private Object data;

}
