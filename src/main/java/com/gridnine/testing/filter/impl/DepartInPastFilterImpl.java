package com.gridnine.testing.filter.impl;

import com.gridnine.testing.filter.DepartInPastFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;

public class DepartInPastFilterImpl implements DepartInPastFilter {
    @Override
    public boolean check(Flight flight) {
        for (Segment segment : flight.getSegmentList()) {
            if (segment.getDepartureDate().isBefore(LocalDateTime.now())) {
                return false;
            }
        }
        return true;
    }
}
