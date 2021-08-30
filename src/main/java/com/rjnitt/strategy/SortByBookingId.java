package com.rjnitt.strategy;

import java.util.Comparator;

import com.rjnitt.model.Booking;

public class SortByBookingId implements Comparator<Booking> {

	@Override
	public int compare(Booking o1, Booking o2) {
		return (o2.getId() - o1.getId());
	}

}
