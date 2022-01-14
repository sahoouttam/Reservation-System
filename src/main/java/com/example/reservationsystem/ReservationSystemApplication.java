package com.example.reservationsystem;

import java.util.HashMap;
import java.util.Map;

import com.example.reservationsystem.Business.Capacity;
import com.example.reservationsystem.Business.ReservationType;
import com.example.reservationsystem.Business.User;
import com.example.reservationsystem.Persistence.CapacityRepository;
import com.example.reservationsystem.Persistence.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ReservationSystemApplication {

	private Map<ReservationType, Integer> capacities = new HashMap<>(){
		{
			put(ReservationType.GYM, 20);
			put(ReservationType.POOL, 4);
			put(ReservationType.SAUNA, 1);
		}
	};
	public static void main(String[] args) {
		SpringApplication.run(ReservationSystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner localData(UserRepository userRepository, CapacityRepository capacityRepository) {
		return (args) -> {
			userRepository.save(new User("ABCDE", "QWERTY", bCryptPasswordEncoder().encode("12345")));

			for (ReservationType reservationType : capacities.keySet()) {
				capacityRepository.save(new Capacity(reservationType, capacities.get(reservationType)));
			}
		};
	}

	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
