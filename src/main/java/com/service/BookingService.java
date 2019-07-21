package com.service;

import com.domain.Booking;
import com.domain.Space;
import com.model.BookingModel;
import com.repository.BookingRepository;
import com.repository.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    SpaceRepository spaceRepository;

    @Autowired
    BookingRepository bookingRepository;

    public List<Space> getAvailableSpaces(String from, String to, Integer categoryId){
        final List<Space> spaceList = new ArrayList<>();

        if(categoryId != null && from == null && to == null){
            spaceList.addAll(spaceRepository.findByCategoryId(categoryId));
        }else if(from != null && to != null){
            //get list of space ids that have bookings within the given date
            List<Integer> spaceIds = bookingRepository.findSpacesWithConflictingBookings(
                    Timestamp.valueOf(from + ":00"), Timestamp.valueOf(to + ":00"));

            if(spaceIds.size() > 0) {
                //get all spaces that are not in the list
                if (categoryId == null) {
                    spaceList.addAll(spaceRepository.findByIdNotIn(spaceIds));
                } else {
                    spaceList.addAll(spaceRepository.findByIdNotInAndCategoryId(spaceIds, categoryId));
                }
            }else{
                if (categoryId == null) {
                    spaceRepository.findAll().forEach(space -> spaceList.add(space));
                } else {
                    spaceList.addAll(spaceRepository.findByCategoryId(categoryId));
                }
            }
        }else{
            spaceRepository.findAll().forEach(space -> spaceList.add(space));
        }

        return spaceList;
    }

    public boolean bookSpace(BookingModel bookingModel){
        boolean isSuccessful = false;

        Booking booking = new Booking(CommonService.getCurrentUserId(), bookingModel.getSpaceId(),
                Timestamp.valueOf(bookingModel.getFrom() + ":00"), Timestamp.valueOf(bookingModel.getTo() + ":00"));

        try {
            booking = bookingRepository.save(booking);
            isSuccessful = true;
        }finally {

        }

        return isSuccessful;
    }
}
