package com.shofydrop.dto;


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
