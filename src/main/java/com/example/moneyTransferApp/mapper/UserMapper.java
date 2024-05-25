package com.example.moneyTransferApp.mapper;


import com.example.moneyTransferApp.entity.Role;
import com.example.moneyTransferApp.entity.Users;
import com.example.moneyTransferApp.model.request.UsersRequest;
import com.example.moneyTransferApp.model.response.RoleResponse;
import com.example.moneyTransferApp.model.response.UsersResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {
                DefaultMapper.class
        }
)
public interface UserMapper {
    Users toEntity(UsersRequest request);
    UsersResponse toResponse(Users user);
    RoleResponse toResponse(Role source);
    void update(@MappingTarget Users user, UsersRequest source);
}
