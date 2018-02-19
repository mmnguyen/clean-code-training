package com.exxeta.cleancode.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DemoApplication.class);
//		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(String... strings) {
		if (strings.length > 0) {
			System.out.println("Do something fancy ");
			Arrays.stream(strings).forEach(s -> {
				if (s.contains("csvpath=")) {
					System.out.println(s.replace("csvpath=", ""));
				}
			});
		} else {
			System.out.println("hmm args is empty");
		}
	}
}
