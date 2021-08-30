package com.rjnitt.model;

public class IdInstance {

	private static int id = 0;

	public static int getNextId() {
		++id;
		return id;
	}
}
