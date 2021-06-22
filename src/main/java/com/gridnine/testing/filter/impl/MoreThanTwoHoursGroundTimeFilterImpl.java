package com.gridnine.testing.filter.impl;

import com.gridnine.testing.filter.MoreThanTwoHoursGroundTimeFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MoreThanTwoHoursGroundTimeFilterImpl implements MoreThanTwoHoursGroundTimeFilter {
    @Override
    public boolean check(Flight flight) {
        if (flight.getSegmentList().size() > 1) {
            List<Segment> segmentList = flight.getSegmentList();
            long groundTime = 0;

            for (int i = 0; i < segmentList.size() - 1; i++) {
                LocalDateTime arrival = segmentList.get(i).getArrivalDate();
                LocalDateTime departureNextSegment = segmentList.get(i + 1).getDepartureDate();
                groundTime += arrival.until(departureNextSegment, ChronoUnit.MINUTES);
            }

            int minutesInHour = 60;
            return groundTime / minutesInHour <= 2;
        } else {
            return false;
        }
    }
}