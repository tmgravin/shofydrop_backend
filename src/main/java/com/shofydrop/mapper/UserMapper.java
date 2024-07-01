package com.shofydrop.mapper;

import com.shofydrop.dto.UsersDto;
import com.shofydrop.entity.Users;

public class UserMapper {

    public static UsersDto mapToUserDto(Users users) {
        return new UsersDto(
                users.getId(),
                users.getName(),
                users.getEmail(),
                users.getPassword(),
                users.getPhone(),
                users.getAddress(),
                users.getIsVerified(),
                users.getRole(),
                users.getKycCompleted(),
                users.getCreatedAt(),
                users.getUpdatedAt(),
                users.getLoginType()
        );
    }

    public static Users maptoUsers(UsersDto usersDto) {
        return new Users(
                usersDto.getId(),
                usersDto.getName(),
                usersDto.getEmail(),
                usersDto.getPassword(),
                usersDto.getPhone(),
                usersDto.getAddress(),zz
                usersDto.getRole(),
                usersDto.getKycCompleted(),
                usersDto.getCreatedAt(),
                usersDto.getUpdatedAt(),
                usersDto.getLoginType()
        );
    }
}
