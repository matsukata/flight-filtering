package com.gridnine.testing.util;

import com.gridnine.testing.filter.impl.DepartInPastFilterImpl;
import com.gridnine.testing.filter.impl.DepartBeforeArrivalFilterImpl;
import com.gridnine.testing.filter.impl.MoreThanTwoHoursGroundTimeFilterImpl;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.FlightBuilder;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println("Output without any filter");
        flights.forEach(System.out::println);
        System.out.println("Output without a flight departing in the past");
        flights.stream().filter(x -> new DepartInPastFilterImpl().check(x)).forEach(System.out::println);
        System.out.println("Output without a flight that departs before it arrives");
        flights.stream().filter(x -> new DepartBeforeArrivalFilterImpl().check(x)).forEach(System.out::println);
        System.out.println("Output without flights with more than two hours ground time");
        flights.stream().filter(x -> new MoreThanTwoHoursGroundTimeFilterImpl().check(x)).collect(Collectors.toList()).forEach(System.out::println);
    }
}
