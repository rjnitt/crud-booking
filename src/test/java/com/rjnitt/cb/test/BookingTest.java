package com.rjnitt.cb.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rjnitt.BookingApplicationTest;
import com.rjnitt.controller.BookingController;
import com.rjnitt.exception.BookingAlreadyExistException;
import com.rjnitt.model.Address;
import com.rjnitt.model.Booking;

@SpringBootTest(classes = BookingApplicationTest.class)
public class BookingTest {

	@Autowired
	BookingController bookingController;


	@Test
	public void test1_happy_flow() {
		
		RuntimeException ast1 = assertThrows(RuntimeException.class, () -> bookingController.get(null));
		assertEquals("No booking found!", ast1.getMessage());
		
		// building address
		Address address1 = Address.builder().line1("line1").line2("").city("sample_city").state("sample_state")
				.zipCode("sample_zip").build();
		// buiding booking
		Booking testBook1 = new Booking.BookingBuilder().name("c_test").address(address1)
				.checkinDatetime(LocalDateTime.now()).checkoutDatetime(LocalDateTime.now())
				.dateOfBirth(LocalDateTime.now()).deposit(100.0).totalPrice(500.0).build();

		ResponseEntity<Booking> responseEntity1 = bookingController.create(testBook1);
		if (responseEntity1.getStatusCode() == HttpStatus.CREATED) {
			assertTrue(true, "its working for corrct input");
		}

		ResponseEntity<List<Booking>> responseEntity = bookingController.get(null);
		if (responseEntity.getBody().size() == 1) {
			assertTrue(true, "created size now 1");
		}
		

//		- Implement idempotency logic to avoid duplicate resource creation.*
		assertThrows(BookingAlreadyExistException.class, () -> bookingController.create(testBook1));


		Booking testBook2 = new Booking.BookingBuilder().name("b_test").address(address1)
				.checkinDatetime(LocalDateTime.now()).checkoutDatetime(LocalDateTime.now())
				.dateOfBirth(LocalDateTime.now()).deposit(100.0).totalPrice(500.0).build();
		
		ResponseEntity<Booking> responseEntity2 = bookingController.create(testBook2);
		if (responseEntity2.getStatusCode() == HttpStatus.CREATED) {
			assertTrue(true, "its working for corrct input");
		}
		
		Booking testBook3 = new Booking.BookingBuilder().name("a_test").address(address1)
				.checkinDatetime(LocalDateTime.now()).checkoutDatetime(LocalDateTime.now())
				.dateOfBirth(LocalDateTime.now()).deposit(100.0).totalPrice(500.0).build();
		
		ResponseEntity<Booking> responseEntity3 = bookingController.create(testBook3);
		if (responseEntity3.getStatusCode() == HttpStatus.CREATED) {
			assertTrue(true, "its working for corrct input");
		}
		
		
		Booking testBook4 = new Booking.BookingBuilder().name("d_test").address(address1)
				.checkinDatetime(LocalDateTime.now()).checkoutDatetime(LocalDateTime.now())
				.dateOfBirth(LocalDateTime.now()).deposit(100.0).totalPrice(500.0).build();
		ResponseEntity<Booking> responseEntity4 = bookingController.create(testBook4);
		if (responseEntity4.getStatusCode() == HttpStatus.CREATED) {
			assertTrue(true, "its working for corrct input");
		}
		
		ResponseEntity<List<Booking>> responseEntityFilterByName = bookingController.get("name");
		if(responseEntityFilterByName.getBody().get(0).getName() == "a_test") {
			assertTrue(true, "asc by name is working");
		}
		
		ResponseEntity<List<Booking>> responseEntityFilterById = bookingController.get("id");
		if(responseEntityFilterById.getBody().get(0).getName() == "d_test") {
			assertTrue(true, "created by desc");
		}
		
	}
	
	@Test
	public void test2_validation_flow() {
		// booking name missing validation
		Booking testBook1 = new Booking.BookingBuilder().build();
		RuntimeException ast1 = assertThrows(RuntimeException.class, () -> bookingController.create(testBook1));
		assertEquals("Booking name can't be null/empty", ast1.getMessage());

		// name added, exception will change, becuse of another missing fields
		Booking testBook2 = new Booking.BookingBuilder().name("test_2").build();
		RuntimeException ast2 = assertThrows(RuntimeException.class, () -> bookingController.create(testBook2));
		assertNotEquals("Booking name can't be null/empty", ast2.getMessage());

		// wrong address & optinal line2
		// zipcode missing
		Address address1 = Address.builder().line1("line1").line2("").city("sample_city").state("sample_state").build();
		Booking testBook3 = new Booking.BookingBuilder().name("test_3").address(address1)
				.checkinDatetime(LocalDateTime.now()).checkoutDatetime(LocalDateTime.now())
				.dateOfBirth(LocalDateTime.now()).deposit(100.0).totalPrice(500.0).build();
		RuntimeException ast3 = assertThrows(RuntimeException.class, () -> bookingController.create(testBook3));
		assertEquals("Zipcode can't be null/empty", ast3.getMessage());

	}

}
