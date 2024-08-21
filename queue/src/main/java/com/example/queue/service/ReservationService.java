package com.example.queue.service;

import com.example.queue.dto.ReservationRequestDTO;
import com.example.queue.model.Reservation;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class ReservationService {
    private List<Reservation> reservations = new ArrayList<Reservation>();

    public Reservation createReservation(ReservationRequestDTO reservationRequestDTO) throws Exception{
        if((reservationRequestDTO.getReservationDate().getDayOfWeek() == DayOfWeek.WEDNESDAY) || (reservationRequestDTO.getReservationDate().getDayOfWeek() == DayOfWeek.FRIDAY))
            throw new BadRequestException("No reservations available on Wednesday and Friday");

        List<Reservation> checkReservation = reservations.stream().filter(r -> r.getReservationTime().isEqual(reservationRequestDTO.getReservationDate())).toList();
        if(checkReservation.size() >= 2)
            throw new BadRequestException("Reservations are full for the day");
        Reservation reservation = new Reservation(UUID.randomUUID(), reservationRequestDTO.getCustomerName(), reservationRequestDTO.getReservationDate());
        this.reservations.add(reservation);
        return reservation;
    }

    public List<Reservation> getCurrentWeekReservations() {
        return this.reservations.stream().filter(reservation -> {
            Calendar target = GregorianCalendar.from(reservation.getReservationTime().atStartOfDay(ZoneId.systemDefault()));
            Calendar current = Calendar.getInstance();
            return (target.get(Calendar.WEEK_OF_YEAR) == current.get(Calendar.WEEK_OF_YEAR)) && (target.get(Calendar.YEAR) == current.get(Calendar.YEAR));
        }).toList();
    }
}
