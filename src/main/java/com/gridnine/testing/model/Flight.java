package com.gridnine.testing.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Flight {
    private final List<Segment> segmentList;

    public Flight(final List<Segment> segments) {
        segmentList = segments;
    }

    public List<Segment> getSegmentList() {
        return segmentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(segmentList, flight.segmentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(segmentList);
    }

    @Override
    public String toString() {
        return segmentList.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }
}
