package com.rjnitt.strategy;

import java.util.List;

import com.rjnitt.model.Booking;

public interface BookingListFilter {

	List<Booking> getBookingListByFilter(List<Booking> list);
}
