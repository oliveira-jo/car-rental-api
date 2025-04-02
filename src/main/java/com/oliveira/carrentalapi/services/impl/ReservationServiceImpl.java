package com.oliveira.carrentalapi.services.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.oliveira.carrentalapi.domain.dtos.request.ReservationRequestDto;
import com.oliveira.carrentalapi.domain.dtos.response.ReservationResponseDto;
import com.oliveira.carrentalapi.domain.enums.ReservationStatus;
import com.oliveira.carrentalapi.domain.enums.UserRole;
import com.oliveira.carrentalapi.domain.exceptions.BusinessException;
import com.oliveira.carrentalapi.domain.exceptions.ObjectNotFoundException;
import com.oliveira.carrentalapi.domain.mapper.ReservationMapper;
import com.oliveira.carrentalapi.domain.models.Category;
import com.oliveira.carrentalapi.domain.models.Reservation;
import com.oliveira.carrentalapi.domain.models.User;
import com.oliveira.carrentalapi.repositories.CategoryRepository;
import com.oliveira.carrentalapi.repositories.ReservationRepository;
import com.oliveira.carrentalapi.repositories.UserRepository;
import com.oliveira.carrentalapi.services.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

  private final UserRepository userRepository;
  private final CategoryRepository categoryRepository;
  private final ReservationRepository reservationRepository;
  private final ReservationMapper reservationMapper;

  public ReservationServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository,
      ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
    this.userRepository = userRepository;
    this.categoryRepository = categoryRepository;
    this.reservationRepository = reservationRepository;
    this.reservationMapper = reservationMapper;
  }

  @Transactional(rollbackOn = Exception.class)
  @Override
  public ReservationResponseDto save(ReservationRequestDto reservationRequestDto, UUID userId) {

    Optional<User> userFomDB = userRepository.findById(userId);
    if (!userFomDB.isPresent()) {
      throw new ObjectNotFoundException("User not found with provide id");
    }

    Optional<Category> category = categoryRepository.findById(reservationRequestDto.groupID());
    if (!category.isPresent()) {
      throw new ObjectNotFoundException("Category not found with a provide id");
    }

    // VALIDATE DATE VALUES
    if (reservationRequestDto.pickUpDate().isAfter(reservationRequestDto.returnDate())) {
      throw new BusinessException("The pickUpDate can't be after the returnDate");

    } else if (reservationRequestDto.pickUpDate().isBefore(LocalDateTime.now())) {
      throw new BusinessException("The pickUpDate can't be before now");

    } else if (reservationRequestDto.pickUpDate().isAfter(LocalDateTime.now().plusMonths(4))) {
      throw new BusinessException("You can't do reservation after  months later!");

    }

    // CALCULATE RESERVATION REANTAL VALUES
    Long qtdReservation = reservationRequestDto.pickUpDate().until(reservationRequestDto.returnDate(), ChronoUnit.DAYS);
    float preValue = qtdReservation * category.get().getValue();
    BigDecimal totalValue = BigDecimal.valueOf(preValue).setScale(2, RoundingMode.HALF_UP);

    // CREATE A RESERVATION
    Reservation reservation = new Reservation();
    reservation.setPickUpDate(reservationRequestDto.pickUpDate());
    reservation.setReturnDate(reservationRequestDto.returnDate());
    reservation.setQtdDays(qtdReservation);
    reservation.setDailyRentalValue(category.get().getValue());
    reservation.setTotalValue(totalValue);
    reservation.setUser(userFomDB.get());
    reservation.setCategory(category.get());

    reservation.setCreatedAt(LocalDateTime.now());
    reservation.setCreateBy(userFomDB.get().getId());
    reservation.setStatus(ReservationStatus.CONFIRMED);

    return reservationMapper.toReservationResponseDto(
        reservationRepository.save(reservation));

  }

  @Transactional(rollbackOn = Exception.class)
  @Override
  public ReservationResponseDto cancel(UUID reservationId, UUID userId) {

    Optional<User> userFomDB = userRepository.findById(userId);
    if (!userFomDB.isPresent()) {
      throw new ObjectNotFoundException("User not found with provide id");
    }

    Optional<Reservation> resercation = reservationRepository.getReservationById(reservationId);
    if (!resercation.isPresent()) {
      throw new ObjectNotFoundException("Reservation not found with a provide id");
    }

    resercation.get().setStatus(ReservationStatus.CANCELED);
    resercation.get().setUpdateAt(LocalDateTime.now());
    resercation.get().setUpdateBy(userFomDB.get().getId());

    return reservationMapper.toReservationResponseDto(
        this.reservationRepository.save(resercation.get()));

  }

  @Override
  public List<ReservationResponseDto> getAll(Authentication auth) {

    User userLogged = (User) auth.getPrincipal();

    if (userLogged.getRole().equals(UserRole.CLIENT)) {
      return this.reservationRepository.getAllByUserId(userLogged.getId()).stream()
          .map(reservationMapper::toReservationResponseDto)
          .toList();

    } else if (userLogged.getRole().equals(UserRole.ADMIN) || userLogged.getRole().equals(UserRole.SUPPORT)) {
      return this.reservationRepository.findAll().stream()
          .map(reservationMapper::toReservationResponseDto)
          .toList();

    } else {
      return new ArrayList<ReservationResponseDto>();

    }

  }

  @Override
  public ReservationResponseDto findById(Authentication auth, UUID id) {

    User userLogged = (User) auth.getPrincipal();

    Optional<Reservation> reservationFromDB = this.reservationRepository.findById(id);

    if (userLogged.getRole().equals(UserRole.ADMIN) || userLogged.getRole().equals(UserRole.SUPPORT)) {

      return reservationFromDB.map(reservationMapper::toReservationResponseDto)
          .orElseThrow(() -> new ObjectNotFoundException("Reservation not found with provided id"));

    } else if (userLogged.getRole().equals(UserRole.CLIENT)
        && reservationFromDB.get().getUser().getId().equals(userLogged.getId())) {

      return reservationFromDB.map(reservationMapper::toReservationResponseDto).get();

    } else {
      throw new BusinessException("UNAUTHORIZED Access to resercation id: " + id);

    }

  }

}
