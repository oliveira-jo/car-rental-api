package com.oliveira.carrentalapi.domain.dtos;

public record CategoryDto(
                String categoryName,
                String datails,
                int numBigSuitCases,
                int numSmallSuitCases,
                int numOfPeople,
                boolean complete,
                Float value

) {

}
