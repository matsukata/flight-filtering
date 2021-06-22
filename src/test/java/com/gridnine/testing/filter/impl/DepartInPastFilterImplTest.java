package com.gridnine.testing.filter.impl;

import com.gridnine.testing.exception.FlightBuilderException;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class DepartInPastFilterImplTest {
    private static final List<Flight> testFlights = buildTestFlights();
    private static final List<Flight> testFlightsForFiltering = buildTestFlightsForFiltering();

    @Test
    public void check() {
        assertArrayEquals(testFlightsForFiltering.stream().filter(x -> new DepartInPastFilterImpl().check(x)).toArray(), testFlights.toArray());
    }

    private static List<Flight> buildTestFlightsForFiltering() {
        return Arrays.asList(
                //A normal flight with two hour duration
                createFlight(LocalDateTime.of(2021, 6, 23, 2, 53), LocalDateTime.of(2021, 6, 23, 4, 53)),
                //A normal multi segment flight
                createFlight(LocalDateTime.of(2021, 6, 23, 2, 53), LocalDateTime.of(2021, 6, 23, 4, 53),
                        LocalDateTime.of(2021, 6, 23, 5, 53), LocalDateTime.of(2021, 6, 23, 7, 53)),
                //A flight departing in the past
                createFlight(LocalDateTime.of(2021, 6, 17, 2, 53), LocalDateTime.of(2021, 6, 23, 2, 53)),
                //A flight that departs before it arrives
                createFlight(LocalDateTime.of(2021, 6, 23, 2, 53), LocalDateTime.of(2021, 6, 22, 20, 53)),
                //A flight with more than two hours ground time
                createFlight(LocalDateTime.of(2021, 6, 23, 2, 53), LocalDateTime.of(2021, 6, 23, 4, 53),
                        LocalDateTime.of(2021, 6, 23, 7, 53), LocalDateTime.of(2021, 6, 23, 8, 53)),
                //Another flight with more than two hours ground time
                createFlight(LocalDateTime.of(2021, 6, 23, 2, 53), LocalDateTime.of(2021, 6, 23, 4, 53),
                        LocalDateTime.of(2021, 6, 23, 5, 53), LocalDateTime.of(2021, 6, 23, 6, 53)));
    }

    private static List<Flight> buildTestFlights() {
        return Arrays.asList(
                //A normal flight with two hour duration
                createFlight(LocalDateTime.of(2021, 6, 23, 2, 53), LocalDateTime.of(2021, 6, 23, 4, 53)),
                //A normal multi segment flight
                createFlight(LocalDateTime.of(2021, 6, 23, 2, 53), LocalDateTime.of(2021, 6, 23, 4, 53),
                        LocalDateTime.of(2021, 6, 23, 5, 53), LocalDateTime.of(2021, 6, 23, 7, 53)),
                //A flight departing in the past
                //createFlight(LocalDateTime.of(2021, 06, 17, 02, 53), LocalDateTime.of(2021, 06, 23, 02, 53)),
                //A flight that departs before it arrives
                createFlight(LocalDateTime.of(2021, 6, 23, 2, 53), LocalDateTime.of(2021, 6, 22, 20, 53)),
                //A flight with more than two hours ground time
                createFlight(LocalDateTime.of(2021, 6, 23, 2, 53), LocalDateTime.of(2021, 6, 23, 4, 53),
                        LocalDateTime.of(2021, 6, 23, 7, 53), LocalDateTime.of(2021, 6, 23, 8, 53)),
                //Another flight with more than two hours ground time
                createFlight(LocalDateTime.of(2021, 6, 23, 2, 53), LocalDateTime.of(2021, 6, 23, 4, 53),
                        LocalDateTime.of(2021, 6, 23, 5, 53), LocalDateTime.of(2021, 6, 23, 6, 53)));
    }

    private static Flight createFlight(final LocalDateTime... dates) {
        if ((dates.length % 2) != 0) {
            throw new FlightBuilderException(
                    "Even number of dates should be provided");
        }
        List<Segment> segments = new ArrayList<>(dates.length / 2);
        for (int i = 0; i < (dates.length - 1); i += 2) {
            segments.add(new Segment(dates[i], dates[i + 1]));
        }
        return new Flight(segments);
    }
}