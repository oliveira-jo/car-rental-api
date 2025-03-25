package com.oliveira.carrentalapi.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.oliveira.carrentalapi.domain.dtos.ReservationResponseDto;
import com.oliveira.carrentalapi.domain.models.Reservation;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationMapper {

  ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

  @Mapping(source = "user.login", target = "user")
  @Mapping(source = "category.categoryName", target = "category")
  ReservationResponseDto toReservationResponseDto(Reservation reservation);

}
