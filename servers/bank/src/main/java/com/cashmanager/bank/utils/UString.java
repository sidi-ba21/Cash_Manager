package com.cashmanager.bank.utils;

import java.util.concurrent.ThreadLocalRandom;

public class UString {

	public static String randomNumber(int length) {
		if (length <= 0) {
			throw new IllegalArgumentException("The length must be GT 0");
		}

		StringBuilder randomNumber = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int digit = ThreadLocalRandom.current().nextInt(0, 10);
			randomNumber.append(digit);
		}

		return randomNumber.toString();
	}

}
