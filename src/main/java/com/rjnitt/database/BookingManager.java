package com.rjnitt.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rjnitt.exception.BookingAlreadyExistException;
import com.rjnitt.model.Booking;

import lombok.Getter;

@Repository
public class BookingManager {

	@Getter
	Map<String, Booking> bookingMap;

	@Getter
	Map<Integer, Booking> bookingIdMap;

	private BookingManager() {
		this.bookingMap = new HashMap<>();
		this.bookingIdMap = new HashMap<>();
	}
	
//	public static BookingManager getInstace() {
//		if (bookManager == null) {
//			bookManager = new BookingManager();
//		}
//		return bookManager;
//	}
//	
//	static BookingManager bookManager = null;

	public void validateBooking(String name) {
		if (bookingMap.containsKey(name)) {
			throw new BookingAlreadyExistException("This Booking name already exist");
		}
	}

	public void validateBooking(int id) {
		if (bookingIdMap.containsKey(id)) {
			throw new BookingAlreadyExistException("This Booking id already exist");
		}
	}

	public void saveBooking(Booking res) {
		validateBooking(res.getId());
		validateBooking(res.getName());
		
		bookingIdMap.put(res.getId(), res);
		bookingMap.put(res.getName(), res);
	}

	public List<Booking> findAll() {
		Collection<Booking> values = bookingIdMap.values();
		return new ArrayList<>(values);
	}


	

}
