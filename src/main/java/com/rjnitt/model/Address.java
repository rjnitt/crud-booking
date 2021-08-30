package com.rjnitt.model;

import com.rjnitt.util.BookingHelper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Address {

	private String line1;
	private String line2;
	private String city;
	private String state;
	private String zipCode;

	public void validateBasicAddress() {
		validateLine1();
		validateCity();
		validateState();
		validateZipCode();
	}

	private void validateZipCode() {
		if (BookingHelper.validateString(zipCode)) {
			throw new RuntimeException("Zipcode can't be null/empty");
		}
	}

	private void validateState() {
		if (BookingHelper.validateString(state)) {
			throw new RuntimeException("State can't be null/empty");
		}
	}

	private void validateCity() {
		if (BookingHelper.validateString(city)) {
			throw new RuntimeException("city can't be null/empty");
		}

	}

	private void validateLine1() {
		if (BookingHelper.validateString(line1)) {
			throw new RuntimeException("Line1 can't be null/empty");
		}

	}

	// resource. Address will
	// have`line1`,`line2`,`city`,`state`and`zip_code`elements.`line2`
}
