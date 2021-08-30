package com.rjnitt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rjnitt.database.BookingManager;
import com.rjnitt.exception.NoBookingFound;
import com.rjnitt.model.Booking;
import com.rjnitt.model.IdInstance;
import com.rjnitt.service.api.BookingResource;
import com.rjnitt.strategy.BookingFilterByAlpha;
import com.rjnitt.strategy.BookingListFilter;
import com.rjnitt.strategy.DefaultBookingFilter;

@Service
public class BookingResourceImpl implements BookingResource {

	@Autowired
	BookingManager bookingRepository;

	private static Logger LOGGER = LoggerFactory.getLogger(BookingResourceImpl.class);

//	public BookingResourceImpl() {
//		bookingRepository = BookingManager.getInstace();
//	}

	@Override
	public ResponseEntity<Booking> create(Booking booking) {
		LOGGER.info("create service called for BookingResourceImpl : {}", booking);
		booking.validateForCreate();
		int id = IdInstance.getNextId();
//			Booking book = Booking.builder().id(id).name(booking.getName()).dateOfBirth(booking.getDateOfBirth())
//					.checkinDatetime(booking.getCheckinDatetime()).checkoutDatetime(booking.getCheckoutDatetime())
//					.totalPrice(booking.getTotalPrice()).deposit(booking.getDeposit()).address(booking.getAddress())
//					.build();

		Booking book = new Booking.BookingBuilder().id(id).name(booking.getName()).dateOfBirth(booking.getDateOfBirth())
				.checkinDatetime(booking.getCheckinDatetime()).checkoutDatetime(booking.getCheckoutDatetime())
				.totalPrice(booking.getTotalPrice()).deposit(booking.getDeposit()).address(booking.getAddress())
				.build();

		bookingRepository.saveBooking(book);
		System.out.println("created booking success: " + book);
		return ResponseEntity.status(HttpStatus.CREATED).body(book);

	}

	@Override
	public ResponseEntity<List<Booking>> get(String filter) {
		LOGGER.info("get service called for BookingResourceImpl filter: {}", filter);
		List<Booking> list = bookingRepository.findAll();

		if (list.isEmpty()) {
			throw new NoBookingFound("No booking found!");
		}

		// Strategy pattern
		BookingListFilter listFilterStretgy = null;
		if (filter == null || filter.isEmpty() || filter.equals("id")) {
			listFilterStretgy = new DefaultBookingFilter();
		} else {
			listFilterStretgy = new BookingFilterByAlpha();
		}
		
		list = listFilterStretgy.getBookingListByFilter(list);

		return ResponseEntity.status(HttpStatus.OK).body(list);

	}

}
