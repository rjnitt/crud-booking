package com.rjnitt.util;

import java.time.LocalDateTime;

import org.springframework.util.StringUtils;

public class BookingHelper {

	public static boolean validateString(String str) {
		if (StringUtils.isEmpty(str)) {
			return true;
		}
		return false;
	}

	public static void validateString(String str, String fieldName) {
		if (StringUtils.isEmpty(str)) {
			throw new RuntimeException(fieldName + " can't be null/empty");
		}
	}

	public static void validateDatetime(LocalDateTime dateOfBirth, String fieldName) {
		if (dateOfBirth == null) {
			throw new RuntimeException(fieldName + " can't be null/empty");
		}
	}

	public static <T> void validate(T key, String fieldName) {
		if (key == null) {
			throw new RuntimeException(fieldName + " can't be null/empty");
		}
	}

	public static void validateLong(Double key, String fieldName) {
		if (key == null ||  key == 0.0) {
			throw new RuntimeException(fieldName + " can't be null/empty");
		}
	}

}
