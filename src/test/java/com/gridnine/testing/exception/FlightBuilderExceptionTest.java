package com.gridnine.testing.exception;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlightBuilderExceptionTest {
    @Rule
    public final ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void createFlights() {
        expectedEx.expect(FlightBuilderException.class);
        expectedEx.expectMessage("Even number of dates should be provided");
        List<Flight> flights = Arrays.asList(
                //Uneven number of dates
                createFlight(LocalDateTime.of(2021, 6, 23, 2, 53)),
                //Even number of dates
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