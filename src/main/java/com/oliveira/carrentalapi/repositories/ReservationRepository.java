package com.oliveira.carrentalapi.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.oliveira.carrentalapi.domain.models.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

  Optional<Reservation> getReservationById(UUID id);

  Page<Reservation> getAllByUserId(UUID id, Pageable pageable);

  Optional<Reservation> findByUserId(UUID id);

}
