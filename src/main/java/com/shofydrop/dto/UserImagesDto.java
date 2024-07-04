package com.shofydrop.dto;

import com.shofydrop.entity.Users;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserImagesDto {
     private Long id;
     private String imageUrl;
     private Timestamp createdAt;
     private Timestamp updatedAt;
}
