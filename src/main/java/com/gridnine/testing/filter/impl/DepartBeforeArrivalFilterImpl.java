package com.gridnine.testing.filter.impl;

import com.gridnine.testing.filter.DepartBeforeArrivalFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.util.List;

public class DepartBeforeArrivalFilterImpl implements DepartBeforeArrivalFilter {
    @Override
    public boolean check(Flight flight) {
        if (flight.getSegmentList().size() == 1) {
            LocalDateTime departureDate = flight.getSegmentList().get(0).getDepartureDate();
            LocalDateTime arrivalDate = flight.getSegmentList().get(0).getArrivalDate();
            return !departureDate.isAfter(arrivalDate);
        } else {
            List<Segment> segmentList = flight.getSegmentList();

            for (int i = 0; i < segmentList.size() - 1; i++) {
                LocalDateTime departureDate = segmentList.get(i).getDepartureDate();
                LocalDateTime arrivalDate = segmentList.get(i).getArrivalDate();
                LocalDateTime nextSegmentDepartureDate = segmentList.get(i + 1).getDepartureDate();
                if (departureDate.isAfter(arrivalDate) || arrivalDate.isAfter(nextSegmentDepartureDate)) {
                    return false;
                }
            }
        }
        return true;
    }
}
