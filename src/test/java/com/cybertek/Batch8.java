package com.cybertek;

import com.github.javafaker.Faker;

public class Batch8 {

	public static void main(String[] args) {
		
		Faker faker = new Faker();
		String cc = faker.finance().creditCard();
		String name = faker.name().firstName();
		String lastName = faker.name().lastName();
		System.out.println("Name on the card: "+name+" "+ lastName);
		System.out.println("Credit card number:"+cc);
		
		
		
		
	}
}
