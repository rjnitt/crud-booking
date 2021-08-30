package com.rjnitt.strategy;

import java.util.Comparator;

import com.rjnitt.model.Booking;

public class SortByBookingName implements Comparator<Booking> {

	@Override
	public int compare(Booking o1, Booking o2) {
		return o1.getName().compareTo(o2.getName());
	}

}
