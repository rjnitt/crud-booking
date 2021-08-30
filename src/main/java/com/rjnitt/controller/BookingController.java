package com.rjnitt.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rjnitt.model.Booking;
import com.rjnitt.service.api.BookingResource;

@RestController
public class BookingController {

	BookingResource bookingRes;

	public BookingController(BookingResource gameS) {
		this.bookingRes = gameS;
	}

	@RequestMapping(value = "/v1/bfs/booking", method = RequestMethod.POST)
	public ResponseEntity<Booking> create(@RequestBody Booking booking) {
		ResponseEntity<Booking> setupGame = bookingRes.create(booking);
		return setupGame;
	}

	@RequestMapping(value = "/v1/bfs/booking", method = RequestMethod.GET)
	public ResponseEntity<List<Booking>> get(@RequestParam(required = false) String filter) {
		ResponseEntity<List<Booking>> responseEntity = bookingRes.get(filter);
		return responseEntity;
	}

}
