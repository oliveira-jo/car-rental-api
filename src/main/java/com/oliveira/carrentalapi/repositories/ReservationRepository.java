package com.oliveira.carrentalapi.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oliveira.carrentalapi.domain.models.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

  Optional<Reservation> getReservationById(UUID id);

  List<Reservation> getAllByUserId(UUID id);

}
