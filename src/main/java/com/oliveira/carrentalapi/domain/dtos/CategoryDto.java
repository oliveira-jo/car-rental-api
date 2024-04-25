package com.oliveira.carrentalapi.domain.dtos;

import java.util.UUID;

public record CategoryDto(
    UUID id,
    String categoryName,
    String datails,
    Integer numBigSuitCases,
    Integer numSmallSuitCases,
    Integer numOfPeople,
    Boolean complete,
    Float value) {

}
