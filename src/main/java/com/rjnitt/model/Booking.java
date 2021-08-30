package com.rjnitt.model;

import java.time.LocalDateTime;

import com.rjnitt.util.BookingHelper;

import lombok.Getter;

@Getter
//@NoArgsConstructor
//@Builder
public class Booking {

	private int id;

	private String name;

	private LocalDateTime dateOfBirth;

	private LocalDateTime checkinDatetime;

	private LocalDateTime checkoutDatetime;

	private Double totalPrice;

	private Double deposit;

	private Address address;

	public Booking(BookingBuilder bb) {
		this.id = bb.id;
		this.name = bb.name;
		this.dateOfBirth = bb.dateOfBirth;
		this.checkinDatetime = bb.checkinDatetime;
		this.checkoutDatetime = bb.checkoutDatetime;
		this.totalPrice = bb.totalPrice;
		this.deposit = bb.deposit;
		this.address = bb.address;
	}

	public static class BookingBuilder {

		private int id;

		private String name;

		private LocalDateTime dateOfBirth;

		private LocalDateTime checkinDatetime;

		private LocalDateTime checkoutDatetime;

		private Double totalPrice;

		private Double deposit;

		private Address address;

		public BookingBuilder() {
		}


		public BookingBuilder id(int id) {
			this.id = id;
			return this;
		}

		public BookingBuilder name(String name) {
			this.name = name;
			return this;
		}

		public BookingBuilder dateOfBirth(LocalDateTime dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
			return this;
		}

		public BookingBuilder checkinDatetime(LocalDateTime checkinDatetime) {
			this.checkinDatetime = checkinDatetime;
			return this;
		}

		public BookingBuilder checkoutDatetime(LocalDateTime checkoutDatetime) {
			this.checkoutDatetime = checkoutDatetime;
			return this;
		}

		public BookingBuilder totalPrice(Double totalPrice) {
			this.totalPrice = totalPrice;
			return this;
		}

		public BookingBuilder deposit(Double deposit) {
			this.deposit = deposit;
			return this;
		}

		public BookingBuilder address(Address address) {
			this.address = address;
			return this;
		}
		
		public Booking build() {
			return new Booking(this);
		}


	}

	public void validateForCreate() {
		validateName();
		validateDate();
		validatePrices();
		validateAddress();
	}

	private void validateDate() {
		validateDob();
		validateCheckInDatetime();
		validateCheckOutDatetime();
		
	}

	private void validatePrices() {
		validateTotalPrice();
		validateDeposit();
		validateAmt();
	}

	private void validateAmt() {
		if (deposit > totalPrice) {
			throw new RuntimeException("Deposit could not be more than total price");
		}
	}

	private void validateAddress() {
		BookingHelper.validate(address, "Address");
		address.validateBasicAddress();
	}

	private void validateDeposit() {
		BookingHelper.validateLong(deposit, "Booking Deposit");
	}

	private void validateTotalPrice() {
		BookingHelper.validateLong(totalPrice, "Booking Total Price");
		

	}

	private void validateCheckOutDatetime() {
		BookingHelper.validateDatetime(checkoutDatetime, "Booking Checkout");
		
		if(checkoutDatetime.isBefore(checkinDatetime)) {
			throw new RuntimeException("Checkout time will be greated than check in time");
		}
	}

	private void validateCheckInDatetime() {
		BookingHelper.validateDatetime(checkinDatetime, "Booking Checkin");
	}

	private void validateDob() {
		BookingHelper.validateDatetime(dateOfBirth, "Booking DOB");
		if(LocalDateTime.now().isBefore(dateOfBirth)) {
			throw new RuntimeException("DOB will be less than current time");
		}
		
	}

	private void validateName() {
		BookingHelper.validateString(name, "Booking name");
	}

	public Booking(int id, String name, LocalDateTime dateOfBirth, LocalDateTime checkinDatetime,
			LocalDateTime checkoutDatetime, double totalPrice, double deposit, Address address) {
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.checkinDatetime = checkinDatetime;
		this.checkoutDatetime = checkoutDatetime;
		this.totalPrice = totalPrice;
		this.deposit = deposit;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", name=" + name + "]";
	}

//	public Booking(int id, Booking booking) {
//		Booking build = new BookingBuilder()
//				.id(id)
//				.name(booking.getName())
//				.dateOfBirth(booking.getDateOfBirth())
//				.checkinDatetime(booking.getCheckinDatetime())
//				.checkoutDatetime(booking.getCheckoutDatetime())
//				.totalPrice(booking.getTotalPrice())
//				.deposit(booking.getDeposit())
//				.address(booking.getAddress())
//				.build();
//		
////		return new BookingBuilder(id, booking.getName(), booking.getDateOfBirth(), booking.getCheckinDatetime(),
////				booking.getCheckoutDatetime(), booking.getTotalPrice(), booking.getDeposit(), booking.getAddress()).build(); 
////		return new Booking(id, booking.getName(), booking.getDateOfBirth(), booking.getCheckinDatetime(),
////				booking.getCheckoutDatetime(), booking.getTotalPrice(), booking.getDeposit(), booking.getAddress());
//	}

}
