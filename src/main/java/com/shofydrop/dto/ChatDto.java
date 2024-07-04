package com.shofydrop.dto;

import com.shofydrop.entity.Users;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ChatDto {
    private Long id;
    private Users users;
    private String message;
    private boolean read;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
