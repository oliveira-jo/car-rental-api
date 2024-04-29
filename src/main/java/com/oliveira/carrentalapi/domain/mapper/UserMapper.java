package com.oliveira.carrentalapi.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.oliveira.carrentalapi.domain.dtos.UserDto;
import com.oliveira.carrentalapi.domain.models.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

  UserDto userToUserDto(User user);

}
