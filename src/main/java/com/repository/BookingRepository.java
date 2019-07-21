package com.repository;

import com.domain.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {

    //@Query("SELECT spaceId FROM Booking WHERE from <= ?1 AND to >= ?2")
    @Query("select spaceId from Booking b " +
            "where (b.from <= ?1 and b.to >= ?1) " +
            "or (b.from >= ?2 and b.to <= ?2)" )
    List<Integer> findSpacesWithConflictingBookings(Timestamp start, Timestamp end);
}
