package com.oliveira.carrentalapi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oliveira.carrentalapi.domain.models.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

}
