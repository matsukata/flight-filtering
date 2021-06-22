package com.gridnine.testing.filter;

public interface Filter<Flight> {
    boolean check(Flight flight);
}
