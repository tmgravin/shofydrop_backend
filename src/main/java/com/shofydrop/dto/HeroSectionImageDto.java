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
public class HeroSectionImageDto {
    private Long id;
    private String imageUrl;
    private String altText;
    private String redirectUrl;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
