package com.shofydrop.dto;

import com.shofydrop.entity.Users;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RewardDto {
    private Long id;
    private int point;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}