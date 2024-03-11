package com.example.es16.Controller;

import com.example.es16.entities.Flight;
import com.example.es16.entities.Status;
import com.example.es16.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    FlightRepository flightRepository;
    @PostMapping
    public List<Flight> createFlights (@RequestParam(required = false) Integer numberFlights) {
        if (numberFlights == null) {
            numberFlights = 100;
        }
        List<Flight> flightList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i<numberFlights; i++) {
            Flight flight = new Flight();
            flight.setDescription("descrizione" + random.nextInt(100));
            flight.setFromAirport("airport" + random.nextInt(100));
            flight.setToAirport("airport" + random.nextInt());
            flight.setStatus(Status.ONTIME);
            flightList.add(flight);

        } return flightRepository.saveAll(flightList);
    }
    @GetMapping("/allFlightsAsc")
    public Page<Flight> getAllFlightsAsc (@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return flightRepository.getAllFlightAsc(pageable);
    }
    @GetMapping("/flightsOnTime")
    public List<Flight> getAllOnTime () {
        List<Flight> flightList = flightRepository.findAll();
        List<Flight> onTimeList = new ArrayList<>();
        for (Flight flight : flightList) {
            if (flight.getStatus().equals(Status.ONTIME)) {
                onTimeList.add(flight);
            }
        }
        return onTimeList;
    }
    @GetMapping("/status")
    public List<Flight> findStatus (@RequestParam(name = "p1") Status p1, @RequestParam(name = "p2") Status p2) {
    List<Flight> flightList = flightRepository.findStatus(p1, p2);
    return flightList;
    }
}
