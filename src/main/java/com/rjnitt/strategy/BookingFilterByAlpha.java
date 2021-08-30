package com.rjnitt.strategy;

import java.util.Collections;
import java.util.List;

import com.rjnitt.model.Booking;

public class BookingFilterByAlpha implements BookingListFilter {

	@Override
	public List<Booking> getBookingListByFilter(List<Booking> list) {
		Collections.sort(list, new SortByBookingName());
		return list;
	}

}
