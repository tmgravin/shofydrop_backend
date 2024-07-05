package com.shofydrop.dto;

import com.shofydrop.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ChatDto {
    @NotBlank(message = "Message cannot be null")
    private String message;

    @PastOrPresent
    private Timestamp sendAt;

}
