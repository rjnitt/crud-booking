package com.rjnitt.strategy;

import java.util.Collections;
import java.util.List;

import com.rjnitt.model.Booking;

public class DefaultBookingFilter implements BookingListFilter {

	@Override
	public List<Booking> getBookingListByFilter(List<Booking> list) {
		Collections.sort(list, new SortByBookingId());
		return list;
	}

}
