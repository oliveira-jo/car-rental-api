package com.oliveira.carrentalapi.domain.dtos;

public record CategoryDto(
        String categoryName,
        String datails,
        Integer numBigSuitCases,
        Integer numSmallSuitCases,
        Integer numOfPeople,
        Boolean complete,
        Float value

) {

}
