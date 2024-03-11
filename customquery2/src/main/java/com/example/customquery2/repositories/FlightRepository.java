package com.example.customquery2.repositories;

import com.example.customquery2.entities.Flight;
import com.example.customquery2.entities.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query(value = "SELECT * FROM FLIGHT ORDER BY FLIGHT.from_Airport ASC", nativeQuery = true)
    Page<Flight> getAllFlightAsc (Pageable pageable);

    @Query(value = "SELECT * FROM FLIGHT WHERE FLIGHT.status = :p1 OR FLIGHT.STATUS = :p2", nativeQuery = true)
    List<Flight> findStatus (Status p1, Status p2);
}
