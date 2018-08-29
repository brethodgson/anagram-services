package com.anagrams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.anagrams")
public class AnagramsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnagramsApplication.class, args);
	}
}