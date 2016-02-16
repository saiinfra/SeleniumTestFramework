package com.test;

import java.io.IOException;

public class TestException {

	public static void p() throws IOException {

		
		throw new IOException();

	
	}

	public static void m() {

		try {
			p();
		} catch (Exception e) {
			System.out.println("Exception Handled");
		}

	}

	public static void main(String[] args) {

		m();

	}

}
